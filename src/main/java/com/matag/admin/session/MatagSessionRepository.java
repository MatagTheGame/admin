package com.matag.admin.session;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MatagSessionRepository extends CrudRepository<MatagSession, Long> {
  Optional<MatagSession> findBySessionId(String sessionId);

  @Query("SELECT COUNT(sessionId) FROM MatagSession WHERE validUntil > ?1")
  long countOnlineUsers(LocalDateTime now);

  @Modifying
  @Transactional
  @Query("DELETE FROM MatagSession WHERE validUntil < ?1")
  int deleteValidUntilBefore(LocalDateTime now);

  void deleteBySessionId(String sessionId);

  boolean existsBySessionId(String sessionId);
}
