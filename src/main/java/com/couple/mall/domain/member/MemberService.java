package com.couple.mall.domain.member;

import com.couple.mall.domain.common.ErrorCode;
import com.couple.mall.domain.common.ResponseException;
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
public class MemberService {
    private final MemberRepository memberRepository;
    private final MessageSource messageSource;

    @Transactional(readOnly = true)
    public HashMap<String, Boolean> findByEmail(String params, Locale locale) throws ResponseException {
        try{
            HashMap<String, Boolean> response = new HashMap<>();
            Member byEmail = memberRepository.findByEmail(params);
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


    @Transactional(readOnly = true)
    public HashMap<String, String> register(MemberRegisterRequest params,Locale locale) throws ResponseException {
        try {
            HashMap<String, String> response = new HashMap<>();
            String email = memberRepository.save(params.toEntity()).getEmail();
            response.put("email", email);
            return response;
        }catch (Exception e){
            throw new ResponseException(ErrorCode.ERROR_PERSISTENCE, messageSource.getMessage("common.data.error.exception",null,locale));
        }
    }
}
