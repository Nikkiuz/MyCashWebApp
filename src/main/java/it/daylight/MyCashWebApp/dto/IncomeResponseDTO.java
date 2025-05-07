package it.daylight.MyCashWebApp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncomeResponseDTO {
	private Long id;
	private String description;
	private Double amount;
	private LocalDateTime date;
	private String category;
	private Long userId;
	private String userName;
}
