package com.couple.mall.domain.api.login;

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
public class LoginApiController {
    private final LoginService loginService;
    private final Locale locale = new Locale("ko", "KR");

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, BindingResult bindingResult){
        try {
            if(bindingResult.hasErrors()) {
                HashMap<String, String> data = new HashMap<>();
                ObjectError error = bindingResult.getAllErrors().get(0);
                data.put("message" , error.getDefaultMessage());
                return new ResponseEntity<>(data,null, HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(loginService.login(request,locale));
        }catch(ResponseException ex) {
            return new ResponseEntity<>(ex.getData(),null,ex.getCode());
        }
    }
}
//GIT TEST 1 입니다. 