package com.couple.mall.web.register;

import com.couple.mall.domain.member.MemberService;
import com.couple.mall.domain.member.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final MemberService registerService;

    @GetMapping("/auth/signup")
    public String registerRequest(@ModelAttribute("registerRequest") MemberRegisterRequest request){
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String register(@Valid @ModelAttribute MemberRegisterRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "auth/signup";
        }

        return "index";
    }
}
