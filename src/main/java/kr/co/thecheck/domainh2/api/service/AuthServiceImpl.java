package kr.co.thecheck.domainh2.api.service;

import kr.co.thecheck.domainh2.api.utils.AuthCodeGenerator;
import kr.co.thecheck.domainh2.domain.authLog.service.AuthLogService;
import kr.co.thecheck.domainh2.domain.user.entity.Member;
import kr.co.thecheck.domainh2.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberService memberService;

    private final AuthLogService authLogService;

    private final AuthCodeGenerator authCodeGenerator;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthCode auth(RequestAuthCodeDto reqDto) throws Exception {

        // 사용자 정보 조회
        Optional<Member> member = memberService.findByUsername(reqDto.getUsername());

        // 사용자 인증
        // 인증에 실패하면 예외발생
        authCheck(member, reqDto.getPassword());

        // 인증 코드 생성
        AuthCode authCode = authCodeGenerator.generate(reqDto.getUsername());

        // 다른 디바이스에서 로그인되어있는지 확인
        // 이미 로그인 중이면 예외 발생
        authLogService.alreadyLoginCheck(member.get().getId(), authCode.getAuthCode());

        // 인증 코드 테이블에 저장
        // 저장에 실패하면 예외발생
        authLogService.addLog(member.get().getId(), authCode.getAuthCode());

        return authCode;
    }

    @Override
    public Token issueToken(String authCode) throws RuntimeException {
        return null;
    }

    @Override
    public Token reIssueToken(String refreshToken) throws RuntimeException {

        return null;
    }

    private void authCheck(Optional<Member> member, String password) throws RuntimeException{
        if(member.isEmpty())
            throw new RuntimeException("사용자 정보가 없습니다.");

        if(!passwordEncoder.matches(password, member.get().getPassword()))
            throw new RuntimeException("비밀번호가 맞지 않습니다.");
    }
}
