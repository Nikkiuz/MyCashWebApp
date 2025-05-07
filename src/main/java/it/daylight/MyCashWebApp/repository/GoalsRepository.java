package it.daylight.MyCashWebApp.repository;

import it.daylight.MyCashWebApp.entity.Goals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GoalsRepository extends JpaRepository<Goals, Long> {

	List<Goals> findByUserId(Long userId);

	Goals findByUserIdAndId(Long userId, Long id);

	List<Goals> findByExpirationDate(Date expirationDate);

	List<Goals> findByCategory(String category);

}
