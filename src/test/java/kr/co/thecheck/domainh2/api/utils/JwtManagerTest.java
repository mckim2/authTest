package kr.co.thecheck.domainh2.api.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;


@ExtendWith({SpringExtension.class})
class JwtManagerTest {



    @Test
    public void createJwtCreateTest() throws Exception{
        JwtManager manager = new JwtManager();
        JwtManager.Token testId = manager.createToken("testId");
        System.out.println(testId.getAccessToken());
        System.out.println(testId.getRefreshToken());
    }

    @Test
    public void localDateTest() throws Exception{
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getDayOfMonth()+"/"+now.getHour());
        LocalDateTime plus1 = now.plusHours(1);
        System.out.println(now.getDayOfMonth()+"/"+now.getHour());
        System.out.println(plus1.getDayOfMonth()+"/"+plus1.getHour());
    }

}