package me.kyeongho.sample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SampleController {
	
	@GetMapping("/sample")
	public String sample() {
		throw new SampleException();
	}
	
	@PostMapping("/form")
	@ResponseBody
    public String handleFormUpload(MyForm form, BindingResult errors) {
		System.out.println(form);
        return "Ok";
	}
	
	@PostMapping("/form/requestpart")
	@ResponseBody
	public String handleJsonAndFormUpload(@RequestPart("meta-data") MetaData metadata,
	        @RequestPart("file-data") MultipartFile filedata) {
		System.out.println(metadata);
		System.out.println("[originalFileName=" + filedata.getOriginalFilename() + "]");
	    return "Ok";
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
