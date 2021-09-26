package com.couple.mall.domain.member;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String addressCd;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String addressExact;

    @Column
    private Long defaultAddr;

    @Column
    private String fcmToken;

    @Column(nullable = false)
    private Integer auth;

}
