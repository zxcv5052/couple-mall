package com.couple.mall.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/api/check/email")
    public MemberFindByNicknameOrEmailResponse checkEmailResponse(@RequestBody HashMap<String, String> request){
        return memberService.findByEmail(request.get("email"));
    }

    @PostMapping("/api/check/nickname")
    public MemberFindByNicknameOrEmailResponse checkNicknameResponse(@RequestBody HashMap<String, String> request){
        return memberService.findByNickname(request.get("nickname"));
    }
}
