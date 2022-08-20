package com.server.domain.message.repository;

import com.server.domain.chat.Chat;
import com.server.domain.help.Help;
import com.server.domain.message.Message;
import com.server.domain.user.Onboarding;

public interface MessageRepositoryCustom {

    boolean isRequested(Onboarding sender, Help help);

    Message findRecentMessage(Chat chat);
}
