package kr.co.thecheck.domainh2.domain.authLog.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AuthLogId implements Serializable {

    private String userId;

    private String authCode;

}
