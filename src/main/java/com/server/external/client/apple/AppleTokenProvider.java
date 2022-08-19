package com.server.external.client.apple;

public interface AppleTokenProvider {

    String getSocialIdFromIdToken(String idToken);
}
