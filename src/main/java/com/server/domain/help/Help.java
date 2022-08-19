package com.server.domain.help;

import com.server.domain.common.AuditingTimeEntity;
import com.server.domain.user.Onboarding;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Help extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "onboarding_id")
    private Onboarding onboarding;

    @Column(nullable = false, length = 100)
    private String content;

    @OneToMany(mappedBy = "help", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<HelpImage> helpImages = new ArrayList<>();

    @Builder(access = AccessLevel.PACKAGE)
    private Help(Onboarding onboarding, String content) {
        this.onboarding = onboarding;
        this.content = content;
    }

    public static Help of(Onboarding onboarding, String content) {
        return Help.builder()
                .onboarding(onboarding)
                .content(content)
                .build();
    }
}
