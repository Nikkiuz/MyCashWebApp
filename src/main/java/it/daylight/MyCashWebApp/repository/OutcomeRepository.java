package it.daylight.MyCashWebApp.repository;

import it.daylight.MyCashWebApp.entity.Outcome;
import it.daylight.MyCashWebApp.entity.OutcomeCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Long> {

	List<Outcome> findByUserId(Long userId);

	List<Outcome> findByDate(LocalDateTime date);

	List<Outcome> findByExpirationDate(LocalDateTime expirationDate);

	List<Outcome> findByOutcomeCategories(OutcomeCategories outcomeCategories);

	@Query("SELECT SUM(o.amount) FROM Outcome o WHERE YEAR(o.date) = :year AND MONTH(o.date) = :month" +
		"AND (:userId IS NULL OR o.user.id = :userId)")
	double getMonthlyOutcome(@Param("year") int year, @Param("month") int month, @Param("userId") Long userId);

	@Query("SELECT SUM(o.amount) FROM Outcome o WHERE YEAR(o.date) = :year" +
		"AND (:userId IS NULL OR o.user.id = :userId)")
	double getAnnualOutcome(@Param("year") int year, @Param("userId") Long userId);
}
