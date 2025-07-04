package it.daylight.MyCashWebApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "goals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goals {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Date expirationDate;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private GoalsCategories goalsCategories;

	@Column(nullable = false)
	private double amount;

	@Column(nullable = false)
	private double currentAmount;

	@Column(nullable = false)
	private String description;

	@ManyToMany
	@JoinColumn(name = "user_id")
	private List<User> user;
}
