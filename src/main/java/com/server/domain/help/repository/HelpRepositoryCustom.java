package com.server.domain.help.repository;

import com.server.domain.help.Help;
import com.server.domain.user.Onboarding;

import java.util.List;

public interface HelpRepositoryCustom {

    Help findHelpById(Long helpId);

    List<Help> findOtherHelps(Onboarding onboarding);
}
