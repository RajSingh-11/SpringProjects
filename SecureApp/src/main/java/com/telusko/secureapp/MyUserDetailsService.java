package com.telusko.secureapp;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * We have to use @Service annotation when the class provides service or
 * implements any service interface. otherwise it will give error.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		// Here we have to create the object of user repo to connect with database like
		// what we do in JPA, for every table we create a separate interface which
		// extends JpaRepository interface.
		User user = repo.findByUsername(username);

		/* if user is null, we simply throw the exception */
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		// Since this method returns the UserDetails object but we don't have
		// UserDetails object here its an interface. so we have to create a class name
		// UserPricipal(principal means current user) which implements this UserDetails
		// interface of spring security.

		// Now simply we can return the user with UserPrincipal object.
		return new UserPricipal(user);
	}

}
