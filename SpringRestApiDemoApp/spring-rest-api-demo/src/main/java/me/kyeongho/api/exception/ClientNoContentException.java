package me.kyeongho.api.exception;

import me.kyeongho.common.error.exception.BusinessException;
import me.kyeongho.common.error.exception.ErrorCode;

public class ClientNoContentException extends BusinessException {

	public ClientNoContentException() {
		super(ErrorCode.NO_CONTENT);
	}
	
}
