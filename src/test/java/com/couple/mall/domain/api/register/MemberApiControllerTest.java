package com.couple.mall.domain.api.register;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
        String hasEmail = "zxcv5052@daum.net";
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
        assertThat(responseNotHaveEntity.getBody().get("success")).isEqualTo(false);
        assertThat(responseHaveEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseHaveEntity.getBody().get("success")).isEqualTo(true);
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
        assertThat(responseNotHaveEntity.getBody().get("success")).isEqualTo(false);
        assertThat(responseHaveEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseHaveEntity.getBody().get("success")).isEqualTo(true);
    }

    @Test
    public void 회원_등록() throws Exception {
        //given
        RegisterRequest unPerfectRequest = RegisterRequest.builder().build();

        RegisterRequest perfectRequest = RegisterRequest.builder()
                .email("zxcv5052@daum.net").password("gudtj1004!")
                .nickname("kyeong").name("박형서").address("123").phone("01034151607").dividerAuth("2")
                .addressCd("123123").addressExact("쌍용").build();

        RegisterRequest successRequest = RegisterRequest.builder()
                .email("zxcv5052@daum.net").password("gudtj1004!")
                .nickname("kyeong").name("박형서").address("123").phone("01034151607").dividerAuth("2")
                .addressCd("123123").addressExact("쌍용").build();

        String url = "http://localhost:" + port + "/api/register";

        //when
        ResponseEntity<HashMap> unPerfectResponse
                = restTemplate.postForEntity(url, unPerfectRequest, HashMap.class);
        ResponseEntity<HashMap> perfectResponse
                = restTemplate.postForEntity(url, perfectRequest, HashMap.class);
        ResponseEntity<HashMap> duplicateResponse
                = restTemplate.postForEntity(url, successRequest, HashMap.class);

        //then
        assertThat(unPerfectResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(perfectResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(duplicateResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}