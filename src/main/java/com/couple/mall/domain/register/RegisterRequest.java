package com.couple.mall.domain.register;

import com.couple.mall.domain.jpa.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message="EMAIL IS REQUIRED")
    private String email;
    @NotNull(message = "PASSWORD REQUIRED")
    @Size(min = 8, max = 14, message = "LENGTH HAS TO 8~14")
    private String password;
    @NotNull(message = "NICKNAME IS REQUIRED")
    @Size( min = 4, max = 10, message = "LENGTH HAS TO 4~10")
    private String nickname;
    @NotNull(message = "AUTH IS REQUIRED")
    private String dividerAuth;
    @NotNull(message = "NAME IS REQUIRED")
    @Size( min = 2, max = 10, message = "LENGTH HAS TO 2~10")
    private String name;
    @NotNull(message = "PHONE IS REQUIRED")
    private String phone;
    @NotNull(message = "ADDRESS REQUIRED")
    private String address;
    @NotNull(message = "ADDRESS_CD REQUIRED")
    private String addressCd;
    @NotNull(message = "ADDRESS_EXACT REQUIRED")
    private String addressExact;

    @Builder
    public RegisterRequest(String email, String password, String nickname, String dividerAuth,
                           String name, String phone, String address, String addressCd, String addressExact) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.dividerAuth = dividerAuth;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.addressCd = addressCd;
        this.addressExact = addressExact;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .addressCd(addressCd)
                .auth(dividerAuth)
                .name(name)
                .phone(phone)
                .address(address)
                .addressCd(addressCd)
                .addressExact(addressExact)
                .build();
    }
}
