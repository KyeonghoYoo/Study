package me.kyeong.java8to11.optional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import me.kyeong.java8to11.stream.OnlineClass;

public class OptionalRunner {

	public static void main(String[] args) {
		List<OnlineClass> springClasses = new ArrayList<>();
		
		springClasses.add(new OnlineClass(1L, "spring boot", true));
		springClasses.add(new OnlineClass(2L, "spring data jpa", true));
		springClasses.add(new OnlineClass(3L, "spring mvc", false));
		springClasses.add(new OnlineClass(4L, "spring core", false));
		springClasses.add(new OnlineClass(5L, "rest api development", false));
		
		/* Optional 지원 이전의 문제점
		 * progress가 null이 반환되는 경우도 있기 때문에 NullPointException이 날 수 있음.
		 1. 사람이 짜는 코드이기 때문에 null 처리를 놓칠 수가 있다.
		 2. null 리턴하는 것 자체가 좋은 코드라고 보기 어렵다.
		 하지만 자바8 이전에는 적당한 대안이 없었음.
		 이제껏 return 하는 메소드에서 null 처리를 하거나
		 null을 리턴하고 이 요소를 쓰는 클라이언트가 null처리를 하게끔 하는 전략을 써옴.
		*/ 
//		if(studyDutration != null)
//			System.out.println(studyDutration);
		Optional<OnlineClass> optional = springClasses.stream()
				.filter(oc -> oc.getTitle().startsWith("spring"))
				.findFirst();
		boolean present = optional.isPresent();
		System.out.println(present);
		
		// 값이 들어있다면 아무 문제가 없다. 없다면? NoSuchElementException이 발생
		OnlineClass onlineClass = optional.get();
		System.out.println(onlineClass);
		
		// 방법1 ifPreseont()를 써서 Consumer 함수를 넣어준다.
		optional.ifPresent(oc -> {
			System.out.println(oc.getTitle());
		});
		
		// 방법2 orElse()를 이용하여 값이 없을때의 대신 리턴할 값을 생성 
		// -> eager하게 메소드가 호출되기 때문에 값이 있든 없든 createNewClass()가 호출된다.
		OnlineClass orElse = optional.orElse(createNewClass());
		// orElseGet()은 Supplier를 함수형 인터페이스를 주입할 수 있다.
		// 함수형 인터페이스 이기 때문에 lazy하게 호출할 수 있다. 값이 없을 때만 Supplier가 실행됨.
		OnlineClass orElseGet = optional.orElseGet(() -> createNewClass());
		// orElseThrow() -> 값이 없으면 에러를 throw함.
		OnlineClass orElseThrow = optional.orElseThrow(IllegalStateException::new);
		
		// 있다는 가정하에 실행함
		Optional<OnlineClass> filter = optional
				.filter(oc -> !oc.isClosed());
		System.out.println(filter.isPresent());
		Optional<Long> map = optional.map(oc -> oc.getId());
		System.out.println(map.isPresent());
		
		// flatMap()은 받는 타입이 Optional인 경우에 유용하게 사용할 수 있다.
		// flatMap()을 사용하여 한 꺼풀 벗긴 뒤 반환함.
		Optional<Progress> flatMap = optional.flatMap(OnlineClass::getProgress);
		
	}

	private static OnlineClass createNewClass() {
		return new OnlineClass(0L, "New class", false);
	}
}
