package com.telusko.secureapp;

import org.springframework.data.jpa.repository.JpaRepository;

/** User is class and long is id */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
