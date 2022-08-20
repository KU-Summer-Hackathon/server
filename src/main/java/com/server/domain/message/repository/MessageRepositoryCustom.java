package com.server.domain.message.repository;

import com.server.domain.help.Help;
import com.server.domain.user.Onboarding;

public interface MessageRepositoryCustom {

    boolean isRequested(Onboarding sender, Help help);
}
