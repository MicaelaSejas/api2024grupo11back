package com.uade.tpo.controller.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Long roles;
    
	public String getUsername() {
		return username;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public Long getRoles() {
		return roles;
	}
    
    
    
    
}
