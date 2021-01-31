package me.kyeong.java8to11.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 감싸는 어노테이션인 Retention과 Target의 범위가 같거나 더 넓어야 한다.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
public @interface ChickenContainer {
	
	Chicken[] value();
}
