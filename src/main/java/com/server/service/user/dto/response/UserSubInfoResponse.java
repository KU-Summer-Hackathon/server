package com.server.service.user.dto.response;

import com.server.domain.user.UserSubInfo;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class UserSubInfoResponse {

    private int distance;

    private int lamp;

    private int help;

    private int favor;

    public static UserSubInfoResponse of(UserSubInfo userSubInfo) {
        return UserSubInfoResponse.builder()
                .distance(userSubInfo.getDistance())
                .lamp(userSubInfo.getLamp())
                .help(userSubInfo.getHelp())
                .favor(userSubInfo.getFavor())
                .build();
    }

}
