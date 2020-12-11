package me.kyeongho.sample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

	@GetMapping("/sample")
	public String sample() {
		throw new SampleException();
	}
	
	@ExceptionHandler(SampleException.class)
	public @ResponseBody ResponseEntity<SampleError> sampleError(SampleException e){
		SampleError sampleError = new SampleError();
		sampleError.setStatus(400);
		sampleError.setMessage("error.app.key");
		sampleError.setReason("IDK");
		
		return new ResponseEntity<>(sampleError, HttpStatus.valueOf(sampleError.getStatus()));
	}
}
