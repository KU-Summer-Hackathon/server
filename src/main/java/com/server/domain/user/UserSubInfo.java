package com.server.domain.user;


import com.server.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSubInfo extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int distance;

    @Column(nullable = false)
    private int lamp;

    @Column(nullable = false)
    private int help;

    @Column(nullable = false)
    private int favor;

    private UserSubInfo(int distance, int lamp, int help, int favor) {
        this.distance = distance;
        this.lamp = lamp;
        this.help = help;
        this.favor = favor;
    }

    public static UserSubInfo newInstance() {
        return new UserSubInfo(100, 0, 0, 0);
    }

    public void updateLamp(int lamp) {
        this.lamp = lamp;
    }
}
