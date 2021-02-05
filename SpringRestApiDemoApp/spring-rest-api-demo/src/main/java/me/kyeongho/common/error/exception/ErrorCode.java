package me.kyeongho.common.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
	// Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid HTTP Method"),
    INTERNAL_SERVER_ERROR(500, "C003", "Server Error"),
    
    // API Call
    NAVER_API_UNAUTHORIZED(500, "N001", "네이버 오픈 API 통신 중 인증 에러가 발생하였습니다."),
    NAVER_API_BAD_REQUEST(400, "N002", "잘못된 요청 구문, 또는 유효하지 않은 요청입니다."),
    NAVER_API_ERROR(500, "N003", "네이버 오픈 API 통신 중 알 수 없는 에러가 발생하였습니다."),
    NO_CONTENT(400, "N004", "데이터가 없습니다."),
    
    
    ;
    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
