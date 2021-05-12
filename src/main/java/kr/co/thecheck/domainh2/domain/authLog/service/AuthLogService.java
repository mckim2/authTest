package kr.co.thecheck.domainh2.domain.authLog.service;

import kr.co.thecheck.domainh2.domain.authLog.entity.AuthLog;

public interface AuthLogService {

    public AuthLog find(String userId, String authCode);

    public void addLog(String userId, String authCode) throws Exception;

    public void updateToken(String userId, String authCode, String accessToken, String refreshToken) throws Exception;

    public void updateLogout(String userId, String authCode) throws Exception;

}
