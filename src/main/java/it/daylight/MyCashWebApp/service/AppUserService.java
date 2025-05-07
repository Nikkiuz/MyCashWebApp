package it.daylight.MyCashWebApp.service;


import it.daylight.MyCashWebApp.auth.JwtTokenUtil;
import it.daylight.MyCashWebApp.auth.LoginRequest;
import it.daylight.MyCashWebApp.auth.LoginResponse;
import it.daylight.MyCashWebApp.entity.app_users.AppUser;
import it.daylight.MyCashWebApp.entity.app_users.Role;
import it.daylight.MyCashWebApp.repository.AppUserRepository;
import jakarta.persistence.EntityExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {

	private static final Logger logger = LoggerFactory.getLogger(AppUserService.class);

	@Autowired
	private AppUserRepository appUserRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${spring.mail.username}") // Ottiene l'email dell'Admin
	private String adminEmail;

	public AppUser registerUser(String username, String email, String password, Role role) {
		if (appUserRepository.existsByEmail(email)) {
			throw new EntityExistsException("Email già in uso");
		}

		AppUser appUser = new AppUser();
		appUser.setUsername(username);
		appUser.setEmail(email);
		appUser.setPassword(passwordEncoder.encode(password));
		appUser.setRoles(Set.of(role));

		// Salva utente
		appUserRepository.save(appUser);
		logger.info("Nuovo utente registrato: {}", username);

		// Invia email di conferma all'utente
		try {
			String subject = "Benvenuto in Gestione Scuola!";
			String text = "<h1>Benvenuto, " + username + "!</h1><p>Il tuo account è stato creato con successo.</p>";
		} catch (Exception e) {
			logger.error("Errore nell'invio dell'email a {}: {}", email, e.getMessage());
		}

		// Invia notifica all'Admin
		try {
			String adminNotification = "<h1>Nuovo utente registrato!</h1><p>Username: " + username + "</p><p>Email: " + email + "</p>";
		} catch (Exception e) {
			logger.error("Errore nell'invio della notifica all'admin {}: {}", adminEmail, e.getMessage());
		}

		return appUser;
	}

	public LoginResponse authenticate(LoginRequest loginRequest) {
		System.out.println("➡️ Tentativo di login con email: " + loginRequest.getEmail());

		// Trova l'utente nel database usando l'email
		AppUser user = appUserRepository.findByEmail(loginRequest.getEmail())
			.orElseThrow(() -> {
				System.out.println("❌ Utente non trovato per email: " + loginRequest.getEmail());
				return new UsernameNotFoundException("Utente non trovato");
			});

		System.out.println("🔑 Trovato utente: " + user.getEmail());

		// Autenticazione usando l'email e la password
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(user.getEmail(), loginRequest.getPassword())
		);

		System.out.println("✅ Autenticazione riuscita per: " + user.getEmail());

		String token = jwtTokenUtil.generateToken(user);
		System.out.println("🛡️ Token generato: " + token);

		// Restituiamo solo il token e l'ID utente (senza il ruolo)
		return new LoginResponse(token, user.getId());
	}

	public Optional<AppUser> findByUsername(String username) {
		return appUserRepository.findByUsername(username);
	}
}

