package it.daylight.MyCashWebApp.dto;

import it.daylight.MyCashWebApp.entity.OutcomeCategories;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OutcomeResponseDTO {
	private Long id;
	private String description;
	private Double amount;
	private LocalDateTime date;
	private OutcomeCategories outcomeCategories;
	private Date expirationDate;
	private Long userId;
	private String userName;
}
