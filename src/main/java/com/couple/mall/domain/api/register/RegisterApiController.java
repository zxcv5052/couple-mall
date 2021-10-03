package com.couple.mall.domain.api.register;

import com.couple.mall.domain.execption.ResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RegisterApiController {
    private final RegisterService memberService;
    private final Locale locale = new Locale("ko", "KR");

    @PostMapping("/api/check/email")
    public ResponseEntity<?> checkEmailResponse(@RequestBody HashMap<String, String> request){
        try {
            return ResponseEntity.ok(memberService.findByEmail(request.get("email"),locale));
        }catch(ResponseException ex) {
            log.warn(ex.toString());
            return new ResponseEntity<>(ex.getData(),null,ex.getCode());
        }
    }

    @PostMapping("/api/check/nickname")
    public ResponseEntity<?> checkNicknameResponse(@RequestBody HashMap<String, String> request){
        try {
            return ResponseEntity.ok(memberService.findByNickname(request.get("nickname"),locale));
        }catch(ResponseException ex) {
            log.warn(ex.toString());
            return new ResponseEntity<>(ex.getData(),null,ex.getCode());
        }
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> registerMember(@RequestBody @Valid RegisterRequest request, BindingResult bindingResult){
        try {
            if(bindingResult.hasErrors()) {
                HashMap<String, String> data = new HashMap<>();
                ObjectError error = bindingResult.getAllErrors().get(0);
                data.put("message" , error.getDefaultMessage());
                return new ResponseEntity<>(data,null,HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(memberService.register(request,locale));
        }catch(ResponseException ex) {
            log.warn("warn 발생");
            return new ResponseEntity<>(ex.getData(),null,ex.getCode());
        }
    }
}
