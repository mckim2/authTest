package kr.co.thecheck.domainh2.domain.user.entity;

import kr.co.thecheck.domainh2.infra.entity.AuditEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PROTECTED)
public class Member extends AuditEntity {

    @Id
    @Column(name="member_id")
    private String id;

    private String username;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MemberRole> roles = new ArrayList<>();

    public static Member createUser(String username, String password){
        Member user = new Member();
        user.setId(UUID.fromString(username).toString());
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
