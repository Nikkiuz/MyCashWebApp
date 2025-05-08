package it.daylight.MyCashWebApp.repository;

import it.daylight.MyCashWebApp.entity.Outcome;
import it.daylight.MyCashWebApp.entity.OutcomeCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OutcomeRepository  extends JpaRepository<Outcome, Long> {

	List<Outcome> findByUserId(Long userId);

	List<Outcome> findByDate(LocalDateTime date);

	List<Outcome> findByExpirationDate(LocalDateTime expirationDate);
	List<Outcome> findByOutcomeCategories(OutcomeCategories outcomeCategories);
}
