package it.daylight.MyCashWebApp.repository;

import it.daylight.MyCashWebApp.entity.IncomeCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.daylight.MyCashWebApp.entity.Income;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

	List<Income> findByUserId(Long userId);
	List<Income> findByDate(LocalDateTime date);
	List<Income> findByIncomeCategories(IncomeCategories incomeCategories);

}
