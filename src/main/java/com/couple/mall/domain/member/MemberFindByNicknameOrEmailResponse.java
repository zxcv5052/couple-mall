package com.couple.mall.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberFindByNicknameOrEmailResponse {
    private Long id;
    private String email;
    private String nickname;
    private String name;
}
