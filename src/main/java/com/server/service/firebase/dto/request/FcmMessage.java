package com.server.service.firebase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class FcmMessage {
    private boolean validateOnly;
    private Message message;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Message {
        private Android android;
        private String token;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Android {
        private Data data;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Data {
        private String title;
        private String body;
    }
}
