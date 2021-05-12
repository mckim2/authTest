package kr.co.thecheck.domainh2.infra.model;

import jdk.javadoc.doclet.Reporter;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class Response {

    public static ResponseEntity success(Object object){
        return ResponseEntity.status(HttpStatus.OK).body(object);
    }


}
