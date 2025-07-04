package it.daylight.MyCashWebApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
	private String name;
	private String surname;
	private String email;
	private List<IncomeResponseDTO> incomes;
	private List<OutcomeResponseDTO> outcomes;
	private List<GoalsResponseDTO> goals;
}
