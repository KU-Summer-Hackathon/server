package com.server.domain.user;

import com.server.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Onboarding extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "onboarding")
    private User user;

    @Column(length = 30)
    private String name;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Column
    private int age;

    @Column(length = 30)
    private String phoneNumber;

    @Column(length = 100)
    private String address;

    @Column(length = 300)
    private String imageUrl;

    @Builder(access = AccessLevel.PACKAGE)
    public Onboarding(User user, String name, GenderType gender, int age, String phoneNumber, String address, String imageUrl) {
        this.user = user;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.imageUrl = imageUrl;
    }

    public static Onboarding newInstance() {
        return Onboarding.builder()
                .build();
    }
}

