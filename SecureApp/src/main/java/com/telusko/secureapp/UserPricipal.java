package com.telusko.secureapp;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Since every method here is overridden so we basically return true those which
 * are boolean methods because we are not implementing them as of now.
 */

//Since we need User object, so we create constructor and field of type User class.
@SuppressWarnings("serial")
public class UserPricipal implements UserDetails {

	private User user;

	public UserPricipal(User user) {
		super();
		this.user = user;
	}

	/**
	 * This method is very important here because it gives authorities to user. so
	 * we have to return the collection of authorities but right now we are
	 * returning a unique users with USER authority.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	/** so simply here we can return password of user using user object. */
	@Override
	public String getPassword() {

		return user.getPassword();
	}

	/** so simply here we can return the username of user by using user object */
	@Override
	public String getUsername() {

		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
