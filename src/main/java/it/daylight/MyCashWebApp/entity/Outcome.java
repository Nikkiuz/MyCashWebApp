package it.daylight.MyCashWebApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "outcomes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Outcome {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date date;

	@Column(nullable = false)
	private double amount;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OutcomeCategories outcomeCategories;

	@Column(nullable = false)
	private Date expirationDate;

	@Column(nullable = false)
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
