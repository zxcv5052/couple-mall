package com.couple.mall.domain.member;

import com.couple.mall.domain.common.ErrorCode;
import com.couple.mall.domain.common.ResponseException;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MessageSource messageSource;

    @Transactional(readOnly = true)
    public HashMap<String, Boolean> findByEmail(String params, Locale locale) throws ResponseException {
        try{
            Member byEmail = memberRepository.findByEmail(params);
            HashMap<String, Boolean> response = new HashMap<>();
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
            Member byNickname = memberRepository.findByNickname(params);
            response.put("duplicate", byNickname != null);
            return response;
        }catch (Exception e){
            throw new ResponseException(ErrorCode.ERROR_PERSISTENCE, messageSource.getMessage("common.data.error.exception",null,locale));
        }
    }
}
