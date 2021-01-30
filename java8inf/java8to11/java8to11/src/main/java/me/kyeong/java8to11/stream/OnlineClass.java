package me.kyeong.java8to11.stream;

import java.util.Optional;

import me.kyeong.java8to11.optional.Progress;

public class OnlineClass {

	private Long id;
	
	private String title;
	
	private boolean closed;
	
	private Progress progress;

	public OnlineClass(Long id, String title, boolean closed) {
		super();
		this.id = id;
		this.title = title;
		this.closed = closed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public Optional<Progress> getProgress() {
		return Optional.ofNullable(progress);
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}
}
