package kr.co.thecheck.domainh2.domain.member.entity;

import kr.co.thecheck.domainh2.domain.member.constant.RoleType;
import kr.co.thecheck.domainh2.infra.entity.AuditEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PROTECTED)
public class UserRole extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_role_id")
    private Long id;

    @Column(nullable = false)
    private RoleType role;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

}
