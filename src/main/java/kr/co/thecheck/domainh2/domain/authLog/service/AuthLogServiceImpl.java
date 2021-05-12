package kr.co.thecheck.domainh2.domain.authLog.service;

import kr.co.thecheck.domainh2.domain.authLog.entity.AuthLog;
import kr.co.thecheck.domainh2.domain.authLog.entity.AuthLogId;
import kr.co.thecheck.domainh2.domain.authLog.repository.AuthLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthLogServiceImpl implements AuthLogService{

    private final AuthLogRepository authLogRepository;

    @Override
    public AuthLog find(String userId, String authCode) {
        AuthLogId id = new AuthLogId(userId, authCode);
        Optional<AuthLog> authLog = authLogRepository.findById(id);
        return authLog.orElse(new AuthLog());
    }

    @Override
    public void addLog(String userId, String authCode) throws Exception {
        AuthLog authLog = AuthLog.createLog(userId, authCode);
        // 인증 기록을 등록한다.
        // 등록이 불가능하면 예외를 발생한다.
        authLogRepository.save(authLog);
    }

    @Override
    public void updateToken(String userId, String authCode, String accessToken, String refreshToken) throws Exception {
        // 인증 기록을 가져온다.
        AuthLog authLog = this.find(userId, authCode);

        // Log에 토큰 정보를 등록한다.
        // 조건이 맞지 않아 등록에 실패하면 예외를 발생한다.
        authLog.saveTokenData(accessToken, refreshToken);
    }

    @Override
    public void updateLogout(String userId, String authCode) throws Exception {
        // 인증 기록을 가져온다.
        AuthLog authLog = this.find(userId, authCode);

        // Log에 로그아웃 정보를 등록한다.
        authLog.logout();
    }
}
