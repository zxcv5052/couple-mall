package com.couple.mall.domain.login;

import com.couple.mall.domain.jpa.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    /**
     * @return null
     */
    public LoginResponse login(String loginId, String password){

        return null;
    }
}

