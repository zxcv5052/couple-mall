package com.couple.mall.web.register;

import com.couple.mall.domain.api.register.RegisterService;
import com.couple.mall.domain.api.register.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @GetMapping("/auth/signup")
    public String registerRequest(){
        return "auth/signup";
    }
}
