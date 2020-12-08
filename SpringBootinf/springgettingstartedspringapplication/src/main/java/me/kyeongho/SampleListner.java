package me.kyeongho;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//@Component
public class SampleListner implements ApplicationListener<ApplicationStartingEvent>{
	
	/*
	 *  아래와 같은 이벤트는 어플리케이션 컨텍스트가 생성되기 전에 발생하는 이벤트이기 때문에
	 *  Bean이 생성되지 않아 리스너가 제대로 작동하지 않는다. 하여 SpringBootApplication이 실행될 때 추가로 설정해줘야한다.
	 */
	@Override
	public void onApplicationEvent(ApplicationStartingEvent event) {
		System.out.println("=======================");
		System.out.println("Application is starting");
		System.out.println("=======================");
	}
}
