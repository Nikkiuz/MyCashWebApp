package it.daylight.MyCashWebApp.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain chain) throws ServletException, IOException{
		if(shouldNotFilter(request)) {
			System.out.println("🟡 [JWT Filter] Escludendo il controllo per: " + request.getRequestURI());
			chain.doFilter(request, response);
			return;
		}

		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println("🔍 [JWT Filter] Header Authorization ricevuto: " + requestTokenHeader);

		String email = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				email = jwtTokenUtil.getUsernameFromToken(jwtToken); // 🔹 Ora estrae l'email
				System.out.println("✅ [JWT Filter] Token valido, utente: " + email);
			} catch (IllegalArgumentException e) {
				System.out.println("❌ Impossibile ottenere il token JWT");
			} catch (ExpiredJwtException e) {
				System.out.println("❌ Token JWT scaduto");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT scaduto");
				return;
			}
		} else {
			System.out.println("❌ Token JWT assente o non valido");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT mancante");
			return;
		}

		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(email);
				System.out.println("🔍 [JWT Filter] UserDetails caricato: " + userDetails.getUsername());

				if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					System.out.println("✅ [JWT Filter] Autenticazione impostata per: " + email);
				} else {
					System.out.println("❌ [JWT Filter] Token non valido per l'utente: " + email);
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT non valido");
					return;
				}
			} catch (Exception e) {
				System.out.println("❌ Errore nel caricamento dell'utente dal database: " + e.getMessage());
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Errore nell'autenticazione");
				return;
			}
		}

		chain.doFilter(request, response);
	}

	private final AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		boolean isExcluded = EXCLUDED_URLS.stream().anyMatch(pattern -> antPathMatcher.match(pattern, path));
		System.out.println("🔍 [JWT Filter] Controllo esclusione per path: " + path + " - Escluso: " + isExcluded);
		return isExcluded;
	}

	private static final List<String> EXCLUDED_URLS = Arrays.asList(
		"/api/auth/**",  // ✅ Permetti solo l'autenticazione senza token
		"/swagger-ui/**",
		"/v3/api-docs/**",
		"/error",
		"/sw.js"
	);
}

