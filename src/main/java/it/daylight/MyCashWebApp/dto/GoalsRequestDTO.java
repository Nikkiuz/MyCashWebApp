package it.daylight.MyCashWebApp.dto;

import it.daylight.MyCashWebApp.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GoalsRequestDTO {

	private String name;
	private String description;
	private String category;
	private Double amount;
	private Double currentAmount;
	private Date expirationDate;
	private List<User> user;

}
