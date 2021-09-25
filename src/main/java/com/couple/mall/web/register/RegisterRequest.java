package com.couple.mall.web.register;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class RegisterRequest {
    @NotEmpty
    @Min(8) @Max(14)
    private String userId;

    @NotEmpty
    private String userNickName;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String userAddressCd;

    @NotEmpty
    private String userAddress1;

    @NotEmpty
    private String userAddress2;
}
