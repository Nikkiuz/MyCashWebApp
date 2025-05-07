package it.daylight.MyCashWebApp.auth;

import it.daylight.MyCashWebApp.entity.app_users.Role;
import lombok.Data;

@Data
public class RegisterRequest {
	private String username;
	private String email;
	private String password;
	private Role role;
}
