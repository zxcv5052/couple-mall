package com.couple.mall.domain.api.login;

import com.couple.mall.domain.api.common.Message;
import com.couple.mall.domain.api.register.Member;
import com.couple.mall.domain.api.register.MemberRepository;
import com.couple.mall.domain.execption.ResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final MessageSource messageSource;
    /**
     * @return null
     */
    public Message<?> login(LoginRequest request, Locale locale) throws ResponseException {
        try{
            Member member = memberRepository.findByEmailAndPassword(request).orElse(null);
            return member != null ? Message.success() : Message.fail();
        }catch (Exception e){
            throw new ResponseException(Message.fail(), HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.data.error.exception",null,locale));
        }
    }
}

//test06