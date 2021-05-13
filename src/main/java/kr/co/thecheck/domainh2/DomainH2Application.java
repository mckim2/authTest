package kr.co.thecheck.domainh2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.EntityManager;

@SpringBootApplication
//@PropertySource(value = "classpath:application.yml")
public class DomainH2Application {

    public static void main(String[] args) {
        SpringApplication.run(DomainH2Application.class, args);
    }

    public static void logic(EntityManager em){

    }

}
