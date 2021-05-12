package kr.co.thecheck.domainh2.domain.user.repository;

import kr.co.thecheck.domainh2.domain.user.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {
}
