package com.telusko.springmvcboot.restwebservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.springmvcboot.dao.AlienRepo;
import com.telusko.springmvcboot.modal.Alien;

/**
 * If we know that all the methods here are REst api methods which return JSON
 * or XML than in that case we can use @RestController annotation. And We don't
 * have to use @ResponseBody annotation with @RestController on methods.
 */

//@Controller
@RestController
public class AlienController {

	@Autowired
	AlienRepo repo;

	/** #### This is for REST APIs#### */
	/**
	 * localhost:8080/aliens--GET request for fetching all records through Postman.
	 * 
	 * gives the all records in the table.
	 * 
	 * We don't need Model parameter here because we are not working with session,
	 * we don't need view also, so not any jsp.
	 * 
	 * Whatever we return, the boot search in the application.properties and add
	 * .jsp extension by default, so to send actual data we have to use another
	 * annotation called @ResponseBody, it will send as data not the jsp name.
	 * 
	 * If you want to send it in json format, don't not use toString() in return to
	 * send the data, because it converts it in to string but we want it in json.
	 * 
	 * The java text is converted into json format with the help of jackson
	 * dependency, and it is provided by spring boot itself. we don't need to add
	 * any dependency.
	 */

	// produces = { "application/xml" }, using this attribute we can restrict what
	// we want to return,json or xml.

	@GetMapping(path = "aliens", produces = { "application/xml" })
	// @ResponseBody
	public List<Alien> getAliens() {

		List<Alien> aliens = repo.findAll();
		// int i = 9 / 0; //to test the logging AfterThrowing or AfterReturning
		System.out.println("fetching aliens");
		return aliens;
	}

	/**
	 * This method fetches one record
	 * 
	 * localhost:8080/alien/102
	 * 
	 * if we know that the parameter could be different always, we put that in curly
	 * braces with some name and use @PathVariable with same name to map the value
	 * to the method parameter.
	 */
	/* @GetMapping("alien/104") */
	@GetMapping("alien/{aid}")
	// @ResponseBody
	public Alien getAlien(@PathVariable("aid") int aid) {

		Alien alien = repo.findById(aid).orElse(new Alien(0, ""));// This orElse is here coz we if we don't have matched
																	// id in database, that't why this default will be
																	// called.

		return alien;
	}

	/**
	 * We can have methods with same URI, like here getAliens() and this method has
	 * same URI but both have different mapping. this method using post mapping to
	 * save the data.
	 * 
	 * This method gets Alien values from client, here like postman and saves that
	 * in database.
	 * 
	 * localhost:8080/alien -POST mapping with form data key-value =aid:107,
	 * aname:komal
	 * 
	 * We can specify what type of data we want to send like json or xml from
	 * postman, we have to use content-type:application/json in header and in body
	 * we have to select the raw and write the json format for alien data.
	 * 
	 */

	/*
	 * Here @RequestBody annotation is used to convert json/xml into java object coz
	 * we are accepting data in the form of json/xml from client here is postman.
	 * Like we have used @ResponseBody annotation for converting java object to
	 * json/xml.
	 */
	@PostMapping(path = "alien", consumes = { "application/json" })
	public Alien addAlien(@RequestBody Alien alien) {

		repo.save(alien);

		return alien;
	}
}
