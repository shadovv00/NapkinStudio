package com.napkinstudio.util;

import java.util.Collection;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SessionUtil {

	public UserDetails getUserDetails() {
		UserDetails userDetails = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			userDetails = (UserDetails) auth.getPrincipal();
		}
		return userDetails;
	}

	public UserDetails getUserRoles() {
		UserDetails userDetails = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			@SuppressWarnings("unchecked")
			Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
			System.out.println(authorities.toString());
			for(SimpleGrantedAuthority i : authorities) {
				System.out.println(i.getAuthority());
			}
		}
		return userDetails;
	}
}
