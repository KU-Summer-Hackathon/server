package com.server.domain.user;

import com.server.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "users")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private SocialInfo socialInfo;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(nullable = false, length = 300)
    private String fcmToken;

    private User(String socialId, UserSocialType socialType, String fcmToken) {
        this.socialInfo = SocialInfo.of(socialId, socialType);
        this.status = UserStatus.ACTIVE;
        this.fcmToken = fcmToken;
    }

    public static User newInstance(String socialId, UserSocialType socialType, String fcmToken) {
        return new User(socialId, socialType, fcmToken);
    }

    public void updateFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
