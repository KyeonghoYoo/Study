package me.kyeongho.common;

import lombok.Data;

@Data
public class BaseResponse <T> {
	
	private T data;

	public BaseResponse(T data) {
		super();
		this.data = data;
	}
}
