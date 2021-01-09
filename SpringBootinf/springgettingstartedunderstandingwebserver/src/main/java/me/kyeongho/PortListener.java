package me.kyeongho;

import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;

public class PortListener implements ApplicationListener<ServletWebServerInitializedEvent>{

	@Override
	public void onApplicationEvent(ServletWebServerInitializedEvent event) {
		ServletWebServerApplicationContext context = event.getApplicationContext();
		// 웹서버 context를 얻어 getWebServer를 통해 서버를 얻고, Port를 얻을 수 있다.
		System.out.println(context.getWebServer().getPort());
	}

}
