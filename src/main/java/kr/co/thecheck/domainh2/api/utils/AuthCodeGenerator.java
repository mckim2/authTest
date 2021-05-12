package kr.co.thecheck.domainh2.api.utils;

import kr.co.thecheck.domainh2.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthCodeGenerator {

    private final DateFormat dateFormat;

    public AuthService.AuthCode generate(String userId){

        UUID uuid = UUID.fromString(userId);

        return new AuthService.AuthCode(uuid.toString());
    }

}
