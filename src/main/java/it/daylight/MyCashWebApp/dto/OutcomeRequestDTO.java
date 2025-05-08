package it.daylight.MyCashWebApp.dto;

import it.daylight.MyCashWebApp.entity.OutcomeCategories;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OutcomeRequestDTO {
	private String description;
	private Double amount;
	private OutcomeCategories outcomeCategories;
	private LocalDateTime date;
	private Date expirationDate;
	private Long userId;
}
