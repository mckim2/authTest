package kr.co.thecheck.domainh2.api.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateFormat {

    public String yyyyMMddhhmmss(LocalDateTime date){
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }
}
