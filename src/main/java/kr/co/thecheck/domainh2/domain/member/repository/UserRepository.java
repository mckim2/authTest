package kr.co.thecheck.domainh2.domain.member.repository;

import kr.co.thecheck.domainh2.domain.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
