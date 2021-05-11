package kr.co.thecheck.domainh2.domain.member.repository;

import kr.co.thecheck.domainh2.domain.member.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
