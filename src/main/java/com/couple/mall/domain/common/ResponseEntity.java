package com.couple.mall.domain.common;

/**
 * 웹 호출시 리턴결과
 */
public class ResponseEntity<D> {

    private Header header;
    private D data;
    private boolean success;

    public static <D> ResponseEntity<?> success(D data) {
        ResponseEntity<D> result = new ResponseEntity<>();
        result.setError(ErrorCode.SUCCESS, "OK");
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public static <D> ResponseEntity<D> error(ErrorCode errorCode, String errorMessage, D data) {
        ResponseEntity<D> result = new ResponseEntity<>();
        result.setError(errorCode, errorMessage);
        result.setData(data);
        result.setSuccess(false);
        return result;
    }

    public static ResponseEntity<Void> success() {
        ResponseEntity<Void> result = new ResponseEntity<>();
        result.setError(ErrorCode.SUCCESS, "OK");
        result.setSuccess(true);
        return result;
    }

    public static ResponseEntity<Void> error(ErrorCode errorCode, String errorMessage) {
        ResponseEntity<Void> result = new ResponseEntity<>();
        result.setError(errorCode, errorMessage);
        result.setSuccess(false);
        return result;
    }


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError(ErrorCode errorCode, String errMsg) {
        if (header == null) {
            header = new Header();
        }
        header.setErrCd(errorCode.code);
        header.setErrMsg(errMsg);
    }

    public static class Header {

        private String errCd;
        private String errMsg;

        public String getErrCd() {
            return errCd;
        }

        public void setErrCd(String errCd) {
            this.errCd = errCd;
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }
    }
}