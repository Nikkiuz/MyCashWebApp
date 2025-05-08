package it.daylight.MyCashWebApp.dto;

import it.daylight.MyCashWebApp.entity.IncomeCategories;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncomeRequestDTO {

	private String description;
	private Double amount;
	private LocalDateTime date;
	private IncomeCategories incomeCategories;
	private Long userId;
	private String details;
}
