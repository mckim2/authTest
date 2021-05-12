package kr.co.thecheck.domainh2.domain.user.repository;

import kr.co.thecheck.domainh2.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
