package kr.co.thecheck.domainh2.api.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.PushbackReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JwtManager {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.accessTokenLiveHours}")
    private Long accessTokenPeriod;

    @Value("${jwt.refreshTokenLiveHours}")
    private Long refreshTokenPeriod;

    private static String ISSURE = "thecheck";
    private static String SUBJECT = "platfor";

    // Token 생성
    public Token createToken(String userId) throws RuntimeException{

        // 엑세스, 리프레시 토큰 생성 일시
        LocalDateTime issueDate = LocalDateTime.now();
        LocalDateTime accessTokenExpiresDate = issueDate.plusHours(accessTokenPeriod);
        LocalDateTime refreshTokenExpiresDate = issueDate.plusHours(refreshTokenPeriod);

        return Token.builder()
                .userId(userId)
                .accessToken(createAccessToken(userId, issueDate, accessTokenExpiresDate))
                .accessTokenIssuedDate(issueDate)
                .accessTokenExpiresDate(issueDate.plusHours(accessTokenPeriod))
                .refreshToken(createRefreshToken(issueDate, refreshTokenExpiresDate))
                .refreshTokenIssuedDate(issueDate)
                .refreshTokenExpiresDate(issueDate.plusHours(refreshTokenPeriod))
                .build();
    }

    // Token 검증
    public void validate(String token) throws Exception{

    }

    //=========================== Private Method =================================//

    private String createAccessToken(String userId, LocalDateTime issuedDate, LocalDateTime expiresDate){
        JwtBuilder builder = baseBuilder(issuedDate, expiresDate);
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        return builder.setClaims(claims)
                      .compact();
    }

    private String createRefreshToken(LocalDateTime issuedDate, LocalDateTime expiredDate) {
        JwtBuilder jwtBuilder = baseBuilder(issuedDate, expiredDate);
        return jwtBuilder.compact();
    }

    private JwtBuilder baseBuilder(LocalDateTime issuedDate, LocalDateTime expiresDate) {
        return Jwts.builder()
                .setIssuer(ISSURE)  // 토큰 발급자
                .setSubject(SUBJECT) // 토큰 제목
                .setIssuedAt(DateConverter.toDate(issuedDate))
                .setExpiration(DateConverter.toDate(expiresDate))
                .signWith(
                        SignatureAlgorithm.HS256,
                        secret.getBytes(StandardCharsets.UTF_8)
                );
    }



    //=============================  DTO  ========================================//

    @Getter
    @Setter
    @Builder
    static class Token{
        private String userId;
        private String accessToken;
        private LocalDateTime accessTokenIssuedDate;
        private LocalDateTime accessTokenExpiresDate;
        private String refreshToken;
        private LocalDateTime refreshTokenIssuedDate;
        private LocalDateTime refreshTokenExpiresDate;
    }

}
