package me.kyeong.java8to11.stream;

public class OnlineClass {

	private Long id;
	
	private String title;
	
	private boolean closed;

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
	
}
