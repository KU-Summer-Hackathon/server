package com.server.domain.push.repository;

import com.server.domain.push.Push;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PushRepository extends JpaRepository<Push, Long>, PushRepositoryCustom {
}
