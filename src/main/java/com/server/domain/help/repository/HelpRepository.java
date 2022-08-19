package com.server.domain.help.repository;

import com.server.domain.help.Help;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpRepository extends JpaRepository<Help, Long>, HelpRepositoryCustom {
}
