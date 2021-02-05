package me.kyeongho.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.extern.slf4j.Slf4j;
import me.kyeongho.common.error.ErrorResponse.ApiError;
import me.kyeongho.common.error.exception.BusinessException;
import me.kyeongho.common.error.exception.ErrorCode;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }
    
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        log.error("handleBusinessException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }
    
    @ExceptionHandler(WebClientResponseException.class)
    protected ResponseEntity<ErrorResponse> handleWebClientResponseException(final WebClientResponseException e){
    	log.error("handleWebClientResponseException", e);
    	
    	switch (e.getRawStatusCode()) {
		case 401:
			return createApiErrorResponse(ErrorCode.NAVER_API_UNAUTHORIZED, e);
		case 400:
			return createApiErrorResponse(ErrorCode.NAVER_API_BAD_REQUEST, e);
		default:
	    	return createApiErrorResponse(ErrorCode.NAVER_API_ERROR, e);
		}
    }

	private ResponseEntity<ErrorResponse> createApiErrorResponse(final ErrorCode errorCode, final WebClientResponseException e) {
    	final ErrorResponse response = ErrorResponse.of(errorCode, ApiError.of(e.getMessage(), e.getRawStatusCode()));
    	return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
	}

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
