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

    @Column(nullable = false, length = 300)
    private String fcmToken;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "onboarding_id")
    private Onboarding onboarding;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_sub_info_id")
    private UserSubInfo userSubInfo;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private UserStatus status;


    private User(String socialId, UserSocialType socialType, String fcmToken, Onboarding onboarding, UserSubInfo userSubInfo) {
        this.socialInfo = SocialInfo.of(socialId, socialType);
        this.fcmToken = fcmToken;
        this.onboarding = onboarding;
        this.userSubInfo = userSubInfo;
        this.status = UserStatus.ACTIVE;
    }

    public static User newInstance(String socialId, UserSocialType socialType, String fcmToken, Onboarding onboarding, UserSubInfo userSubInfo) {
        return new User(socialId, socialType, fcmToken, onboarding, userSubInfo);
    }

    public void updateFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
