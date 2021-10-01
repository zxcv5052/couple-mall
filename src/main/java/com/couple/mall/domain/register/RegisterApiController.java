package com.couple.mall.domain.register;

import com.couple.mall.domain.common.ErrorCode;
import com.couple.mall.domain.common.ResponseEntity;
import com.couple.mall.domain.common.ResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
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
            return ResponseEntity.success(memberService.findByEmail(request.get("email"), locale));
        }catch(ResponseException ex) {
            log.warn(ex.toString());
            return ResponseEntity.error(ex.getCode(), ex.getLocalizedMessage());
        }
    }

    @PostMapping("/api/check/nickname")
    public ResponseEntity<?> checkNicknameResponse(@RequestBody HashMap<String, String> request){
        try {
            return ResponseEntity.success(memberService.findByNickname(request.get("nickname"),locale));
        }catch(ResponseException ex) {
            log.warn(ex.toString());
            return ResponseEntity.error(ex.getCode(), ex.getLocalizedMessage());
        }
    }

//    @PostMapping("/api/register")
//    public ResponseEntity<?> registerMember(@RequestBody @Valid RegisterRequest request, BindingResult bindingResult){
//        try {
//            if(bindingResult.hasErrors()) {
//                HashMap<String, String> data = new HashMap<>();
//                bindingResult.getAllErrors().forEach(objectError-> {
//                    data.put("code",objectError.getCode());
//                    data.put("message",objectError.getDefaultMessage());
//                    data.put("name", objectError.getObjectName());
//                });
//                return ResponseEntity.multiErrors(ErrorCode.INVALID_PARAMETER, data);
//            }
//            return ResponseEntity.success(memberService.register(request,locale));
//        }catch(ResponseException ex) {
//            return ResponseEntity.error(ex.getCode(), ex.getLocalizedMessage());
//        }
//    }
}
