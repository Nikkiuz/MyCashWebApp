package it.daylight.MyCashWebApp.repository;

import it.daylight.MyCashWebApp.entity.IncomeCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.daylight.MyCashWebApp.entity.Income;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

	List<Income> findByUserId(Long userId);

	List<Income> findByDate(LocalDateTime date);

	List<Income> findByIncomeCategories(IncomeCategories incomeCategories);

	@Query("SELECT SUM(e.importo) FROM Entrata e WHERE YEAR(e.data) = :anno AND MONTH(e.data) = :mese")
	double getEntrateMensili(@Param("anno") int anno, @Param("mese") int mese);

	@Query("SELECT SUM(e.importo) FROM Entrata e WHERE YEAR(e.data) = :anno")
	double getEntrateAnnuali(@Param("anno") int anno);

}
