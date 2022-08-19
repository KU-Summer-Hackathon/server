package com.server.domain.push.repository;

import com.server.domain.help.Help;

public interface PushRepositoryCustom {

    boolean helpIsApplied(Long senderId, Help help);
}
