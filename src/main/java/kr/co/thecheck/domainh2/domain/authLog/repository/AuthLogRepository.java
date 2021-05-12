package kr.co.thecheck.domainh2.domain.authLog.repository;

import kr.co.thecheck.domainh2.domain.authLog.entity.AuthLog;
import kr.co.thecheck.domainh2.domain.authLog.entity.AuthLogId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthLogRepository extends JpaRepository<AuthLog, AuthLogId> {
}
