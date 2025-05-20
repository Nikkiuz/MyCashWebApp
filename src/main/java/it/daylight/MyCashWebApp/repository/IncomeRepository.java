package it.daylight.MyCashWebApp.repository;

import it.daylight.MyCashWebApp.entity.IncomeCategories;
import it.daylight.MyCashWebApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.daylight.MyCashWebApp.entity.Income;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

	List<Income> findByUserId(Long userId);

	List<Income> findByDate(Date date);

	List<Income> findByIncomeCategories(IncomeCategories incomeCategories);

	List<Income> findByAmount(Double amount);

	List<Income> findByIncomeCategoriesAndDateAndAmountAndUser(IncomeCategories incomeCategories, Date date, Double amount, User user);


	@Query("SELECT SUM(i.amount) FROM Income i WHERE YEAR(i.date) = :year AND MONTH(i.date) = :month" + "AND (:userId" +
		" IS NULL OR i.user.id = :userId)")
	double getMonthlyIncome(@Param("year") int year, @Param("month") int month, @Param("userId") Long userId);

	@Query("SELECT SUM(i.amount) FROM Income i WHERE YEAR(i.date) = :year" +
		"AND (:userId IS NULL OR i.user.id = :userId)")
	double getAnnualIncome(@Param("year") int year, @Param("userId") Long userId);


}
