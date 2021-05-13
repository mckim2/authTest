package kr.co.thecheck.domainh2.domain.user.repository;

import kr.co.thecheck.domainh2.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
