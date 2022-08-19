package com.server.service.auth;

import com.server.service.auth.dto.request.LoginDto;

public interface AuthService {

    Long login(LoginDto request);
}
