package kr.co.thecheck.domainh2.api.controller;

import kr.co.thecheck.domainh2.api.service.AuthService;
import kr.co.thecheck.domainh2.infra.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/oauth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     *
     * 인증코드 발행
     *
     * @return
     */
    @PostMapping("/authorize")
    @Validated
    public ResponseEntity authorize(@RequestBody AuthService.RequestAuthCodeDto authDto) throws Exception{
        AuthService.AuthCode authCode = authService.auth(authDto);
        return Response.success(authCode.getAuthCode());
    }

    /**
     *
     * 토큰 발행
     *
     * @param grant_type    : 인증타입
     * @param code          : 인증코드
     * @param refresh_token : 리프래쉬토큰
     * @return
     */
    @PostMapping("/token")
    public ResponseEntity token( @RequestHeader(required = true)  String grant_type
                                ,@RequestHeader(required = false) String code
                                ,@RequestHeader(required = false) String refresh_token
                                ) throws RuntimeException{
        AuthService.Token token;

        // 1. 토큰 발행
        if(Grant_type.TOKEN_ISSUE.equals(grant_type) && ObjectUtils.isEmpty(code)){
            token = authService.issueToken(code);
        }
        // 2. 토큰 재 발행
        else if(Grant_type.TOKEN_RE_ISSUE.equals(grant_type) && ObjectUtils.isEmpty(refresh_token)){
            token = authService.reIssueToken(refresh_token);
        }
        // 3. 헤더값 부족으로 토큰 발행이 불가능한 경우
        else {
            throw new RuntimeException("토큰을 발급 받기 위한 헤더가 충분하지 않습니다.");
        }

        return Response.success(token);
    }

    //====================================================//
    static class Grant_type{
        static String TOKEN_ISSUE = "authorization_code";
        static String TOKEN_RE_ISSUE = "refresh_token";
    }
}
