package it.daylight.MyCashWebApp.repository;

import it.daylight.MyCashWebApp.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {


}
