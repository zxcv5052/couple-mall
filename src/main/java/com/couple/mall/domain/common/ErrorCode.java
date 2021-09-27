package com.couple.mall.domain.common;

public enum ErrorCode {
    SUCCESS("E0000") // 성공
   , ERROR("E9999") // 비계획 에러 (서버 에러)
   , ERROR_PERSISTENCE("E9001") // DB 에러

    // 인증
   , NO_PERMISSION("E0001") // 접근 권한 없음
   , INVALID_TOKEN("E0002") // 잘못된 토큰
   , EXPIRED_TOKEN("E0003") // 토큰 만료

   , INVALID_PARAMETER("E0004") // 잘못된 파라미터
   , INVALID_JSON("E0005") // Json parse error
   , INVALID_VALUE("E0006") // 유효하지 않은 파라미터
   , DATA_NOT_FOUND("E0007") // 데이터 없음
   , USER_NOT_FOUND("E0008") // 사용자 정보 미존재
   , DATA_DUPLICATE("E0009") // 데이터 중복

   , LOGIN_FAIL("E0010") // 로그인 실패
   , LOGIN_FAIL_ID("E0011") // 로그인 시 아이디 미존재
   , LOGIN_FAIL_PASSWORD("E0012") // 로그인 시 패스워드 틀렸을때
   , INCORRECT_PASSWORD("E0013") // 패스워드가 틀림 (인증된 사용자의 패스워드 변경시)

   , EMPTY_PARAMETER("E0014") // 비어있는 파라미터

        /* end */
    ;

    public final String code;

    private ErrorCode(String code) {
        this.code = code;
    }

    public boolean equalsCode(String anotherCode) {
        return this.code.equalsIgnoreCase(anotherCode);
    }
}
