package kr.co.thecheck.domainh2.infra.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PROTECTED)
public class AuditEntity extends BaseEntity {

    @CreatedBy
    private String createId;

    @CreatedDate
    private LocalDateTime createDt;

    private String updateId;

    private LocalDateTime updateDt;

}
