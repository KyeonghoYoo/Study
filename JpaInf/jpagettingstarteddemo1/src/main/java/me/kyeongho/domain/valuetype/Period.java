package me.kyeongho.domain.valuetype;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Embeddable
@Builder
@EqualsAndHashCode
public class Period {

	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	public Period() {
	}
	
	public Period(LocalDateTime startDate, LocalDateTime endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public LocalDateTime getStartDate() {
		return startDate;
	}
	private void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	private void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

}
