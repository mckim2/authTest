package kr.co.thecheck.domainh2.domain.user.entity;

import kr.co.thecheck.domainh2.domain.member.constant.RoleType;
import kr.co.thecheck.domainh2.infra.entity.AuditEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PROTECTED)
public class Member extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    private String membername;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MemberRole> roles = new ArrayList<>();

    //----------------------//
    public static User createUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
