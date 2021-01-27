package me.kyeong.java8to11;

import java.util.function.Function;

public class Plust10 implements Function<Integer, Integer>{

	@Override
	public Integer apply(Integer intParam) {
		return intParam + 10;
	}

}
