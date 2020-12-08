package me.kyeongho;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class SampleArguments {
	
	private Logger logger = LoggerFactory.getLogger(SampleListner.class);
	
	public SampleArguments(ApplicationArguments arguments) {
		logger.debug("foo: " + arguments.containsOption("foo"));
		logger.debug("bar: " + arguments.containsOption("bar"));
	}
}
