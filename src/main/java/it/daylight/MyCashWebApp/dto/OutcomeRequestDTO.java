package it.daylight.MyCashWebApp.dto;

import it.daylight.MyCashWebApp.entity.OutcomeCategories;
import lombok.Data;

import java.util.Date;

@Data
public class OutcomeRequestDTO {
	private String description;
	private Double amount;
	private OutcomeCategories outcomeCategories;
	private Date date;
	private Date expirationDate;
	private Long userId;
}
