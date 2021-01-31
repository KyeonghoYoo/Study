package me.kyeong.java8to11.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE_PARAMETER) // TYPE_PARAMETER로 @Target을 지정해주면 제네릭과 같은 타입 파라미터에 붙일 수 있다.
@Target(ElementType.TYPE_USE) // 이 어노테이션 타입을 지정하면 모든 데이터에 어노테이션을 붙일 수 있다.
@Repeatable(ChickenContainer.class)
public @interface Chicken {
	String value();
	
}
