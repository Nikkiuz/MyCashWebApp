package it.daylight.MyCashWebApp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthRunner implements ApplicationRunner {

	@Autowired
	private AppUserService appUserService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		createAdminUser();
		createStandardUser();
	}

	private void createAdminUser() {
		Optional<AppUser> adminUser = appUserService.findByUsername("admin");
		if (adminUser.isEmpty()) {
			appUserService.registerUser("admin", "admin@mail.com","adminpwd", "ROLE_ADMIN");
			System.out.println("Admin user created");
		}
	}

	private void createStandardUser() {
		Optional<AppUser> standardUser = appUserService.findByUsername("standard");
		if (standardUser.isEmpty()) {
			appUserService.registerUser("user", "user@mail.com","userpwd", "ROLE_USER");
			System.out.println("Standard user created");
		}
	}
}
