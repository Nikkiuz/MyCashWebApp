package it.daylight.MyCashWebApp.entity;

import it.daylight.MyCashWebApp.entity.app_users.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
	private LocalDate date = LocalDate.now();

	@Column(nullable = false)
	private Long amount;

	@Column(nullable = false)
	private String category;

	@Column(nullable = false)
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
