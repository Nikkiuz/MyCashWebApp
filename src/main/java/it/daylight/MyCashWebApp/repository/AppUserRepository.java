package it.daylight.MyCashWebApp.repository;

import it.daylight.MyCashWebApp.entity.app_users.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	boolean existsByEmail(String email);
	Optional<AppUser> findByEmail(String email);
	Optional<AppUser> findByUsername(String username);
	boolean existsByUsername(String username);
}