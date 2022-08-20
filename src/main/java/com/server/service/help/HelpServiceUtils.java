package com.server.service.help;

import com.server.common.exception.NotFoundException;
import com.server.domain.help.Help;
import com.server.domain.help.repository.HelpRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.server.common.exception.ErrorCode.NOT_FOUND_HELP_EXCEPTION;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HelpServiceUtils {

    public static Help findHelpById(HelpRepository helpRepository, Long helpId) {
        Help help = helpRepository.findHelpById(helpId);
        if (help == null) {
            throw new NotFoundException(String.format("존재하지 않는 Help (%s) 입니다", help), NOT_FOUND_HELP_EXCEPTION);
        }
        return help;
    }
}
