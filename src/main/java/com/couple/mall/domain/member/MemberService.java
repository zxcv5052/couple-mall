package com.couple.mall.domain.member;

import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberFindByNicknameOrEmailResponse findByEmail(String params){
        Member byEmail = memberRepository.findByEmail(params);
        if(byEmail == null) return null;
        MemberFindByNicknameOrEmailResponse response = new MemberFindByNicknameOrEmailResponse();
        BeanUtils.copyProperties(byEmail, response);
        return response;
    }

    @Transactional(readOnly = true)
    public MemberFindByNicknameOrEmailResponse findByNickname(String params){
        Member byNickname = memberRepository.findByNickname(params);
        if(byNickname == null) return null;
        MemberFindByNicknameOrEmailResponse response = new MemberFindByNicknameOrEmailResponse();
        BeanUtils.copyProperties(byNickname, response);
        return response;
    }

}
