package me.kyeongho.sample;

import org.springframework.web.multipart.MultipartFile;

public class MyForm {

    private String name;

    private MultipartFile file;

	public MyForm() {
		
	}
    
	public MyForm(String name, MultipartFile file) {
		this.name = name;
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		if(this.file != null)
			return "MyForm [name=" + name + ", file=" + file.getOriginalFilename() + "]";
		else
			return "MyForm [name=" + name + ", file=null]";
	}
	
}
