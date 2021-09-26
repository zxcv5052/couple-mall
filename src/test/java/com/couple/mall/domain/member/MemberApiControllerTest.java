package com.couple.mall.domain.member;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 이메일_체크() throws Exception {
        //given
        String haveNotEmail = "kyeong@naver.com";
        HashMap<String, String> haveNotHashMap = new HashMap<>();
        haveNotHashMap.put("email", haveNotEmail);
        String hasEmail = "zxcv5052@naver.com";
        HashMap<String, String> haveHashMap = new HashMap<>();
        haveHashMap.put("email", hasEmail);
        String url = "http://localhost:" + port + "/api/check/email";

        //when
        ResponseEntity<MemberFindByNicknameOrEmailResponse> responseNotHaveEntity
                = restTemplate.postForEntity(url, haveNotHashMap, MemberFindByNicknameOrEmailResponse.class);

        ResponseEntity<MemberFindByNicknameOrEmailResponse> responseHaveEntity
                = restTemplate.postForEntity(url, haveHashMap, MemberFindByNicknameOrEmailResponse.class);
        //then
        assertThat(responseNotHaveEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseNotHaveEntity.getBody()).isNull();
        assertThat(Objects.requireNonNull(responseHaveEntity.getBody()).getEmail()).isEqualTo(hasEmail);
    }
    @Test
    public void 닉네임_체크() throws Exception {
        //given
        String haveNotNickName = "aaall";
        HashMap<String, String> haveNotHashMap = new HashMap<>();
        haveNotHashMap.put("nickname", haveNotNickName);
        String hasNickName = "kyeong";
        HashMap<String, String> haveHashMap = new HashMap<>();
        haveHashMap.put("nickname", hasNickName);
        String url = "http://localhost:" + port + "/api/check/nickname";

        //when
        ResponseEntity<MemberFindByNicknameOrEmailResponse> responseNotHaveEntity
                = restTemplate.postForEntity(url, haveNotHashMap, MemberFindByNicknameOrEmailResponse.class);

        ResponseEntity<MemberFindByNicknameOrEmailResponse> responseHaveEntity
                = restTemplate.postForEntity(url, haveHashMap, MemberFindByNicknameOrEmailResponse.class);
        //then
        assertThat(responseNotHaveEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseNotHaveEntity.getBody()).isNull();
        assertThat(Objects.requireNonNull(responseHaveEntity.getBody()).getNickname()).isEqualTo(hasNickName);
    }
}