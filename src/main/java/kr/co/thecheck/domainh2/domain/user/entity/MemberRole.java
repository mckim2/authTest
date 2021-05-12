package kr.co.thecheck.domainh2.domain.user.entity;

import kr.co.thecheck.domainh2.domain.user.constant.RoleType;
import kr.co.thecheck.domainh2.infra.entity.AuditEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PROTECTED)
public class MemberRole extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_role_id")
    private Long id;

    @Column(nullable = false)
    private RoleType role;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

}
