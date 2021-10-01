package com.couple.mall.domain.jpa.member;

import lombok.*;

import javax.persistence.*;

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
    private String phone;

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
    private String auth;

    @Builder    // 초기에 저장할 때만 사용
    public Member(String email, String password, String nickname, String name, String phone, String addressCd,
                  String address, String addressExact, Long defaultAddr, String fcmToken, String auth) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.addressCd = addressCd;
        this.address = address;
        this.addressExact = addressExact;
        this.defaultAddr = defaultAddr;
        this.fcmToken = fcmToken;
        this.auth = auth;
    }
}
