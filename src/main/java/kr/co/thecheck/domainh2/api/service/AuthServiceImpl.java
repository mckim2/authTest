package kr.co.thecheck.domainh2.api.service;

import kr.co.thecheck.domainh2.api.utils.AuthCodeGenerator;
import kr.co.thecheck.domainh2.domain.authLog.service.AuthLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthLogService authLogService;

    private final AuthCodeGenerator authCodeGenerator;

    private final PasswordEncoder encoder;

    @Override
    public AuthCode auth(RequestAuthCodeDto reqDto) throws RuntimeException {

        // 사용자 인증

        // 인증 코드 생성
        AuthCode authCode = authCodeGenerator.generate(reqDto.getUsername());

        // 인증 코드 테이블에 저장
        authLogService.addLog();

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
}
