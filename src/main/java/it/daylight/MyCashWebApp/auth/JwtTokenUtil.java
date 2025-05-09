package it.daylight.MyCashWebApp.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.daylight.MyCashWebApp.entity.app_users.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long jwtExpirationInMs;

	// Estrae il nome utente (email) dal token JWT
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// Estrae la data di scadenza dal token JWT
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	// Estrae un claim specifico dal token JWT
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// Estrae tutti i claims dal token JWT
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
	}

	// Verifica se il token JWT è scaduto
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// Genera un token JWT per l'utente, includendo i ruoli
	public String generateToken(AppUser user) {
		List<String> roles = user.getRoles() != null
				? user.getRoles().stream().map(Enum::name).collect(Collectors.toList())
				: List.of(); // Se roles è null, usa una lista vuota

		String token = Jwts.builder()
				.setSubject(user.getEmail()) // ✅ Ora usa l'email come identificatore
				.claim("roles", roles) // Aggiunge i ruoli come claim
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();

		System.out.println("🛡️ Token generato per " + user.getEmail() + ": " + token); // 🔍 Debug
		return token;
	}

	// Estrae i ruoli dal token JWT
	public List<String> getRolesFromToken(String token) {
		Claims claims = getAllClaimsFromToken(token);
		return claims.get("roles", List.class);
	}

	// Valida il token JWT
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}