package com.couple.mall.web.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data

public class LoginRequest {
    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;
}
