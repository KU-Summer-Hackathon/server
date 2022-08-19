package com.server.domain.help;

import com.server.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HelpImage extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "help_id", nullable = false)
    private Help help;

    @Builder(access = AccessLevel.PACKAGE)
    private HelpImage(String imageUrl, Help help) {
        this.imageUrl = imageUrl;
        this.help = help;
    }

    public static HelpImage of(String imageUrl, Help help) {
        return HelpImage.builder()
                .imageUrl(imageUrl)
                .help(help)
                .build();
    }
}
