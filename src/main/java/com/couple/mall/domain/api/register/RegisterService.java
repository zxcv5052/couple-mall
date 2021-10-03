package com.couple.mall.domain.api.register;

import com.couple.mall.domain.api.common.Message;
import com.couple.mall.domain.execption.ResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegisterService {
    private final MemberRepository memberRepository;
    private final MessageSource messageSource;

    @Transactional(readOnly = true)
    public Message<?> findByEmail(String params, Locale locale) throws ResponseException {
        try{
            Member byEmail = memberRepository.findByEmail(params).orElse(null);
            return byEmail != null ? Message.success() : Message.fail();
        }catch (Exception e){
            throw new ResponseException(Message.fail(),HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.data.error.exception",null,locale));
        }
    }

    @Transactional(readOnly = true)
    public Message<?> findByNickname(String params, Locale locale) throws ResponseException {
        try {
            Member byNickname = memberRepository.findByNickname(params).orElse(null);
            return byNickname != null ? Message.success() : Message.fail();
        }catch (Exception e){
            throw new ResponseException(Message.fail(),HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.data.error.exception",null,locale));
        }
    }
    @Transactional
    public Message<?> register(RegisterRequest request, Locale locale) throws ResponseException {
        try {
            HashMap<String, Boolean> response = new HashMap<>();
            Member findByEmail = memberRepository.findByEmail(request.getEmail()).orElse(null);
            Member findByNickName = memberRepository.findByNickname(request.getNickname()).orElse(null);
            if(findByEmail != null){
                throw new ResponseException(Message.fail(),HttpStatus.BAD_REQUEST, messageSource.getMessage("common.duplicate.data",new Object[]{"이메일"},locale));
            }
            if(findByNickName != null){
                throw new ResponseException(Message.fail(),HttpStatus.BAD_REQUEST, messageSource.getMessage("common.duplicate.data",new Object[]{"닉네임"},locale));
            }
            memberRepository.save(request.toEntity());
            return Message.success(response);
        }catch (ResponseException e){
            throw new ResponseException(Message.fail(),e.getCode(), e.getMessage());
        }catch (Exception e){
            throw new ResponseException(Message.fail(),HttpStatus.INTERNAL_SERVER_ERROR, messageSource.getMessage("common.data.error.exception",null,locale));
        }
    }
}
