package it.daylight.MyCashWebApp.dto;

import it.daylight.MyCashWebApp.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class GoalsResponseDTO {

	private Long id;
	private String name;
	private double amount;
	private double currentAmount;
	private String category;
	private String description;
	private List<User> user;
}
