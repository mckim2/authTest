package kr.co.thecheck.domainh2.domain.authLog.entity;

import kr.co.thecheck.domainh2.domain.member.entity.User;
import lombok.*;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PROTECTED)
public class AuthLog {

    @EmbeddedId
    private AuthLogId authLogId;

    private LocalDateTime authCodeCreateDtm; // 인증코드 발급 시점

    private String accessToken;                // 엑세스토큰
    private LocalDateTime accessTokenCreateDtm; // 엑세스토큰 발급 시점

    private String refreshToken;
    private LocalDateTime refreshTokenCreateDtm; // 리프래쉬토큰 발급 시점

    private Boolean logout;

    //=======================Business Logic==========================//
    public static AuthLog createLog(String userId, String authCode) throws Exception{

        if(ObjectUtils.isEmpty(userId) || ObjectUtils.isEmpty(authCode)){
            throw new Exception("UserId 또는 AuthCode가 필요합니다.");
        }

        AuthLogId authLogId = new AuthLogId();
        authLogId.setUserId(userId);
        authLogId.setAuthCode(authCode);

        AuthLog authLog = new AuthLog();
        authLog.setAuthLogId(authLogId);
        authLog.setAuthCodeCreateDtm(LocalDateTime.now());
        authLog.setLogout(false);

        return authLog;
    }

    // 토큰 정보 저장
    public void saveTokenData(String accessToken, String refreshToken) throws Exception{
        if(ObjectUtils.isEmpty(authLogId) || ObjectUtils.isEmpty(authLogId.getUserId()) || ObjectUtils.isEmpty(authLogId.getAuthCode())){
            throw new Exception("토큰을 생성할 수 있는 정보가 없습니다.");
        }

        if(ObjectUtils.isEmpty(accessToken) || ObjectUtils.isEmpty(refreshToken)){
            throw new Exception("accessToken 이나 refreshToken이 존재하지 않습니다.");
        }

        if(logout){
            throw new Exception("로그아웃된 사용자라 토큰 생성을 할 수 없습니다.");
        }

        if(!ObjectUtils.isEmpty(refreshTokenCreateDtm) && refreshTokenCreateDtm.isAfter(LocalDateTime.now())){
            throw new Exception("토큰 유효일자가 지나서 갱신할 수 없습니다.");
        }

        this.setAccessToken(accessToken);
        this.setAccessTokenCreateDtm(LocalDateTime.now());
        this.setRefreshToken(refreshToken);
        this.setRefreshTokenCreateDtm(LocalDateTime.now());
    }

    public void logout() throws Exception{
        if(logout){
            throw new Exception("이미 로그아웃되어있습니다.");
        }
        logout = true;
    }
}
