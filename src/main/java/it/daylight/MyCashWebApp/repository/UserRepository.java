package it.daylight.MyCashWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.daylight.MyCashWebApp.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	User findByName(String username);

	User findBySurname(String surname);

}
