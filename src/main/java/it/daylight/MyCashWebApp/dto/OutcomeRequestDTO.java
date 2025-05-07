package it.daylight.MyCashWebApp.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OutcomeRequestDTO {
	private String description;
	private Double amount;
	private String category;
	private LocalDateTime date;
	private Date expirationDate;
	private Long userId;
}
