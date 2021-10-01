package com.couple.mall.domain.jpa.member;

import com.couple.mall.domain.register.RegisterRequest;
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
        ResponseEntity<HashMap> responseNotHaveEntity
                = restTemplate.postForEntity(url, haveNotHashMap, HashMap.class);

        ResponseEntity<HashMap> responseHaveEntity
                = restTemplate.postForEntity(url, haveHashMap, HashMap.class);
        //then
        assertThat(responseNotHaveEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> notHaveData = (HashMap<String, Boolean>) responseNotHaveEntity.getBody().get("data");
        assertThat(notHaveData.get("duplicate")).isEqualTo(false);
        assertThat(responseHaveEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> haveData = (HashMap<String, Boolean>) responseHaveEntity.getBody().get("data");
        assertThat(haveData.get("duplicate")).isEqualTo(true);
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
        ResponseEntity<HashMap> responseNotHaveEntity
                = restTemplate.postForEntity(url, haveNotHashMap, HashMap.class);

        ResponseEntity<HashMap> responseHaveEntity
                = restTemplate.postForEntity(url, haveHashMap, HashMap.class);
        //then
        assertThat(responseNotHaveEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> notHaveData = (HashMap<String, Boolean>) responseNotHaveEntity.getBody().get("data");
        assertThat(notHaveData.get("duplicate")).isEqualTo(false);
        assertThat(responseHaveEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> haveData = (HashMap<String, Boolean>) responseHaveEntity.getBody().get("data");
        assertThat(haveData.get("duplicate")).isEqualTo(true);
    }

    @Test
    public void 회원_등록() throws Exception {
        //given
        RegisterRequest unPerfectRequest = RegisterRequest.builder().build();
        RegisterRequest perfectRequest = RegisterRequest.builder().build();
        String url = "http://localhost:" + port + "/api/member";

        //when
        ResponseEntity<HashMap> someRequestEmpty
                = restTemplate.postForEntity(url, unPerfectRequest, HashMap.class);

        ResponseEntity<HashMap> response
                = restTemplate.postForEntity(url, perfectRequest, HashMap.class);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}