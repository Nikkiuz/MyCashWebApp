package it.daylight.MyCashWebApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "outcome")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Outcome {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate date = LocalDate.now();

	@Column(nullable = false)
	private Long amount;

	@Column(nullable = false)
	private String category;

	@Column(nullable = false)
	private Date expirationDate;

	@Column(nullable = false)
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
