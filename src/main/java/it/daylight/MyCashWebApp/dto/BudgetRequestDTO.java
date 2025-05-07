package it.daylight.MyCashWebApp.dto;

import lombok.Data;

@Data
public class BudgetRequestDTO {
	private Double incomeAmount;
	private Double outcomeAmount;
	private Double totalAmount;
	private Long userId;
}
