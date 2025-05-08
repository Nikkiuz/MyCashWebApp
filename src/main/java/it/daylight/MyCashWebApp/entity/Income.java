package it.daylight.MyCashWebApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "incomes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Income {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date date;

	@Column(nullable = false)
	private double amount;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private IncomeCategories incomeCategories;

	@Column(nullable = false)
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
