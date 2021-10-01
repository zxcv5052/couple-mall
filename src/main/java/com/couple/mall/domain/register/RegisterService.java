package com.couple.mall.domain.register;

import com.couple.mall.domain.common.ErrorCode;
import com.couple.mall.domain.common.ResponseException;
import com.couple.mall.domain.jpa.member.Member;
import com.couple.mall.domain.jpa.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
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
    public HashMap<String, Boolean> findByEmail(String params, Locale locale) throws ResponseException {
        try{
            HashMap<String, Boolean> response = new HashMap<>();
            Member byEmail = memberRepository.findByEmail(params)
                    .orElse(null);
            response.put("duplicate", byEmail != null);
            return response;
        }catch (Exception e){
            throw new ResponseException(ErrorCode.ERROR_PERSISTENCE, messageSource.getMessage("common.data.error.exception",null,locale));
        }
    }

    @Transactional(readOnly = true)
    public HashMap<String, Boolean> findByNickname(String params,Locale locale) throws ResponseException {
        try {
            HashMap<String, Boolean> response = new HashMap<>();
            Member byNickname = memberRepository.findByNickname(params)
                    .orElse(null);
            response.put("duplicate", byNickname != null);
            return response;
        }catch (Exception e){
            throw new ResponseException(ErrorCode.ERROR_PERSISTENCE, messageSource.getMessage("common.data.error.exception",null,locale));
        }
    }
}
