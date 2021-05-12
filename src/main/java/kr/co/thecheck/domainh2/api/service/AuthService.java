package kr.co.thecheck.domainh2.api.service;

import kr.co.thecheck.domainh2.api.exception.InvalidRequestException;
import kr.co.thecheck.domainh2.api.exception.UnsupportedResponseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;


/**
 *
 * 인증 서비스
 *
 */
public interface AuthService {

    /**
     *
     * 인증코드 생성
     *
     * @param reqDto
     * @return
     * @throws InvalidRequestException
     * @throws AccessDeniedException
     * @throws UnsupportedResponseType
     */
    public AuthCode auth(RequestAuthCodeDto reqDto) throws RuntimeException;

    /**
     *
     * 토큰 발행
     *
     * @param authCode : 인증코드
     * @return
     * @throws RuntimeException
     */
    public Token issueToken(String authCode) throws RuntimeException;

    /**
     *
     * 토큰 재발행
     *
     * @param refreshToken : 리프래쉬 토큰
     * @return
     * @throws RuntimeException
     */
    public Token reIssueToken(String refreshToken) throws RuntimeException;

    //===================================================//
    //                    Inner Class                    //
    //===================================================//
    @Getter
    @Setter
    @AllArgsConstructor
    class AuthCode{
        String authCode;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class Token{
        String accessToken;
        LocalDateTime expiresAt;
        String refreshToken;
        LocalDateTime refreshTokenExpiresAt;
        LocalDateTime issuedAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class RequestAuthCodeDto{
        @NotNull(message = "username is required")
        private String username;
        @NotNull(message = "password is required")
        private String password;
    }

}
