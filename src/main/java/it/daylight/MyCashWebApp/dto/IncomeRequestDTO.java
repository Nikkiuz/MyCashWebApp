package it.daylight.MyCashWebApp.dto;

import it.daylight.MyCashWebApp.entity.IncomeCategories;
import lombok.Data;
;
import java.util.Date;

@Data
public class IncomeRequestDTO {

	private String description;
	private Double amount;
	private Date date;
	private IncomeCategories incomeCategories;
	private Long userId;
	private String details;
}
