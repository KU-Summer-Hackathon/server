package com.server.service.message.dto.response;

import com.server.domain.help.Help;
import com.server.domain.message.Message;
import com.server.domain.message.MessageType;
import com.server.domain.user.GenderType;
import com.server.domain.user.Onboarding;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class MessageInfoResponse {

    private Long messageId;

    private MessageType type;

    private String helpContent;

    private String messageContent;

    private String userImageUrl;

    private String userName;

    private String userGender;

    private int userAge;

    private String userPhoneNumber;

    public static MessageInfoResponse of(Message message, Help help, Onboarding onboarding) {
        return MessageInfoResponse.builder()
                .messageId(message.getId())
                .type(message.getType())
                .helpContent(help.getContent())
                .messageContent(message.getContent())
                .userImageUrl(onboarding.getImageUrl())
                .userName(onboarding.getName())
                .userGender((onboarding.getGender() == GenderType.MAN) ? "남자" : "여자")
                .userAge(onboarding.getAge())
                .userPhoneNumber(onboarding.getPhoneNumber())
                .build();
    }
}
