package com.telusko.secureapp;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

/**
 * Here we are doing configuration for our own user name and password coz by
 * default the spring boot security gives us user name and password on the
 * console. i don't want to use that, thats why i am writing configuration for
 * our own user name and password.
 * 
 * We extends WebSecurityConfigureAdapter to get all the configuration features
 * of security.
 * 
 * We need to use @Configuration coz we are doing configuration here.
 * 
 * we need to use @EnableWebSecurity annotation coz we want to enable web
 * security here.
 * 
 * we have to override one method here userDetailsService();
 * 
 * and we need object of UserDetailsService here that's why we use
 * annotation @Bean here over method.
 */

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	/** This is in memory implementation */

	/*
	 * @Bean
	 * 
	 * @Override protected UserDetailsService userDetailsService() {
	 * 
	 * // for time being we are using in memory user name and password. In the same
	 * way // we can make multiple users.
	 * 
	 * // User and UserDetails are inbuilt classes in spring security.
	 * List<UserDetails> users = new ArrayList<>();
	 * users.add(User.withDefaultPasswordEncoder().username("Raj").password("1234").
	 * roles("User").build()); return new InMemoryUserDetailsManager(users); }
	 */

	/** This is authentication from database */
	// @Autowired
	// private UserDetailsService userDetailsService;

	/**
	 * AuthenticationProvider interface given by spring boot security to talk to
	 * database.
	 * 
	 * we need object of BCryptPasswordEncoder() thats why @bean annotation
	 */
	// @Bean
	// public AuthenticationProvider authProvider() {

	// Implements AuthenticationProvider
	// DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

	/**
	 * We need a service now which will connect with dao, and that is
	 * UserDetailsService which is an interface, so we create its object by
	 * autowired here. and Since we don't have a service class which will connect
	 * with dao. so we create a Class MyUserDetailsService which implements this
	 * UserDetailsService interface and overrides its method loadUserByUsername() in
	 * our own class.
	 */
	// provider.setUserDetailsService(userDetailsService);
	/**
	 * we set the password encoder here, which will take simple text format but
	 * normally we do not use this normal text format but for time being we are
	 * using it. And there we have to connect with the database and fetch the user
	 * detail with JPA.
	 */
	// provider.setPasswordEncoder(passwordEncoder());

	/**
	 * We are using BCryptPasswordEncoder for passwords, it is a hashing technique
	 * which hashes it multiple time, depending on the number of rounds.
	 * 
	 * we have websites also which generates bcryptpasswords like
	 * https://www.browserling.com/tools/bcrypt
	 * 
	 * So i bcrypt the ABCD and abcd in 10 rounds and saves in the database with
	 * user name =Raj. Now this will work with Raj and Singh only not with other
	 * username like i have Spiderman, Superman because they don't have a bcrypt
	 * password.
	 */
	// provider.setPasswordEncoder(new BCryptPasswordEncoder());
	// return provider;

//	}

	// This was just to check with Nooppassword encoder, where the password is in
	// simple text format , which is not a good idea. So instead of this we have
	// used Bcrypt password encoder for password.
	/*
	 * @SuppressWarnings("deprecation")
	 * 
	 * @Bean public static NoOpPasswordEncoder passwordEncoder1() { return
	 * (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance(); }
	 */

	/** This method is for Google OAuth2 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated();

	}

}
