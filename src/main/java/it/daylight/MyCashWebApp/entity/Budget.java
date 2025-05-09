package it.daylight.MyCashWebApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "budget")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private double incomeAmount;

	@Column(nullable = false)
	private double outcomeAmount;

	@Column(nullable = false)
	private double totalAmount;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
