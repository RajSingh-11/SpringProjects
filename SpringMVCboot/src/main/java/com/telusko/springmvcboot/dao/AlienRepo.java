package com.telusko.springmvcboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.telusko.springmvcboot.modal.Alien;

/**
 * We have written our own Interface AlienRepo which and extended the Interface
 * called JpaRepository given by spring data jpa which handles the Basic CRUD
 * operations like create,Read,Update,Find,delete etc.
 * 
 * Here the parameters are JpaRepository<Class, Primary key Type>
 */
public interface AlienRepo extends JpaRepository<Alien, Integer> {
//Here this method is not in JpaRepository, we have created here manually
	/**
	 * return List<Alien> coz we might have multiple Aliens with same same. This is
	 * the Domain specific query ,the JPA provides us, so depend upon properties or
	 * variable you have, but it does not mean you can do anything you want, you
	 * have to follow a particular rule example:it should start by findBy or getBy
	 * and after that there should be a variable name, and first letter has to be
	 * capital. In the same way you can do more struff like that
	 * example:findByAnameOrderByAname(String aname), findByAnameOrderByAid(String
	 * aname),findByAnameOrderByAidDesc(String aname) etc. you can add OR and AND
	 * here just like we do in SQL.
	 */
	List<Alien> findByAname(String aname);

	List<Alien> findByAnameOrderByAid(String aname);

	List<Alien> findByAnameOrderByAidDesc(String aname);

	/**
	 * we can write our own query also with the help of @Query annotation,
	 * and @Param annotation, which is used for placeholder of params in query. Here
	 * Alien in query is Class, not table.(table name is alien)
	 */
	@Query("from Alien where aname=:name")
	List<Alien> find(@Param("name") String aname);
}
