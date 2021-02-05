package me.kyeongho.common.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kyeongho.common.error.exception.ErrorCode;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String message;
    private int status;
    @JsonInclude(Include.NON_NULL)
    private ApiError apiError;
    private String code;
    
    private ErrorResponse(final ErrorCode code, final ApiError apiError) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.apiError = apiError;
    }

    private ErrorResponse(final ErrorCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.apiError = null;
    }

    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }
    
    public static ErrorResponse of(final ErrorCode code, final ApiError apiError) {
        return new ErrorResponse(code, apiError);
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ApiError {
        private String message;
        private int status;

        private ApiError(final String message, final int status) {
            this.message = message;
            this.status = status;
        }

        public static ApiError of(final String message, final int status) {
            return new ApiError(message, status);
        }
    }
}
