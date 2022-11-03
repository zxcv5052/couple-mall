package com.couple.mall.domain.api.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data

public class LoginRequest {
    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;
}
//GIT TEST 2 입니다. 