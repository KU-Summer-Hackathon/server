package com.server.service.help.dto.response;

import lombok.*;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class GetHelpsResponse {

    private String address;

    private List<HelpInfoResponse> helps;

    public static GetHelpsResponse of(String address, List<HelpInfoResponse> helps) {
        return GetHelpsResponse.builder()
                .address(address)
                .helps(helps)
                .build();
    }
}
