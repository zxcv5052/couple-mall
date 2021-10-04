package com.couple.mall.web.login;

import com.couple.mall.domain.api.login.LoginRequest;
import com.couple.mall.domain.api.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    @GetMapping("/auth/login")
    public String loginForm(){
        return "/auth/signin";
    }
}
