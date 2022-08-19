package com.server.domain.user.repository;

import com.server.domain.user.User;
import com.server.domain.user.UserSocialType;

public interface UserRepositoryCustom {

    boolean existsBySocialIdAndSocialType(String socialId, UserSocialType socialType);

    User findUserById(Long id);

    User findUserBySocialIdAndSocialType(String socialId, UserSocialType socialType);
}
