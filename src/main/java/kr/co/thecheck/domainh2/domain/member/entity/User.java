package kr.co.thecheck.domainh2.domain.member.entity;

import kr.co.thecheck.domainh2.infra.entity.AuditEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PROTECTED)
public class User extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    private String username;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();

}
