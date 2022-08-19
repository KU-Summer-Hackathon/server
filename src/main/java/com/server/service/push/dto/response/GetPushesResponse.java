package com.server.service.push.dto.response;

import lombok.*;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class GetPushesResponse {

    private List<PushInfoResponse> pushes;

    public static GetPushesResponse of(List<PushInfoResponse> pushes) {
        return GetPushesResponse.builder()
                .pushes(pushes)
                .build();
    }
}
