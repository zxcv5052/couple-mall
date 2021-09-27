package com.couple.mall.domain.member;

import com.couple.mall.domain.common.ResponseEntity;
import com.couple.mall.domain.common.ResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;
    private final Locale locale = new Locale("ko", "KR");

    @PostMapping("/api/check/email")
    public ResponseEntity<?> checkEmailResponse(@RequestBody HashMap<String, String> request){
        try {
            return ResponseEntity.success(memberService.findByEmail(request.get("email"), locale));
        }catch(ResponseException ex) {
            return ResponseEntity.error(ex.getCode(), ex.getLocalizedMessage());
        }
    }

    @PostMapping("/api/check/nickname")
    public ResponseEntity<?> checkNicknameResponse(@RequestBody HashMap<String, String> request){
        try {
            return ResponseEntity.success(memberService.findByNickname(request.get("nickname"),locale));
        }catch(ResponseException ex) {
            return ResponseEntity.error(ex.getCode(), ex.getLocalizedMessage());
        }
    }
}
