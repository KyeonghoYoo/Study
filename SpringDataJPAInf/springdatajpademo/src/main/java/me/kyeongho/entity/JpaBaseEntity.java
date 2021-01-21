package me.kyeongho.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public class JpaBaseEntity {
	
	@Column(updatable = false)
	private LocalDateTime createdDate;
	
	private LocalDateTime updatedDate;
	
	// persist되기 전에 실행되는 메소드
	@PrePersist
	public void prePersist() {
		LocalDateTime now = LocalDateTime.now();
		this.createdDate = now;
		this.updatedDate = now;
	}
	
	// update 전에 실행되는 메소드
	@PreUpdate
	public void preUpdate() {
		this.updatedDate = LocalDateTime.now();
	}
}
