package it.daylight.MyCashWebApp.dto;

import lombok.Data;

@Data
public class BudgetResponseDTO {

	private Long id;
	private Double incomeAmount;
	private Double outcomeAmount;
	private Double totalAmount;
	private Long userId;
	private String userName;
}
