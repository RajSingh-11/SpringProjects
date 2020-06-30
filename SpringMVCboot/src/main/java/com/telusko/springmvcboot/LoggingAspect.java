package com.telusko.springmvcboot;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/*We have made this new class to keep it separate from business logic and maintained for logging.*/

/**
 * @Aspect annotation is used to specify that this class is an
 *         aspect. @component is used to specify that the spring should know
 *         this class.
 * 
 *         And we have 5 different types of advice like Before, After, Around
 *         etc. but generally this three are more common to use.
 * 
 * @Before is used to tell that we are using before advice, and inside the
 *         parenthesis we have expression and write the whole path of
 *         getAliens() method.
 * 
 * 
 *         localhost:8080/aliens -> call from postman to test.
 * 
 *         import org.slf4j.Logger - use this 4j logger always.
 */
@Aspect
@Component
public class LoggingAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution(public * com.telusko.springmvcboot.restwebservice.AlienController.getAliens())")
	public void logBefore() {
		System.out.println("get alien method called from aspect.");

		LOGGER.info("get alien method called from aspect.");
	}

	@After("execution(public * com.telusko.springmvcboot.restwebservice.AlienController.getAliens())")
	public void logAfter() {

		LOGGER.info(
				"get alien method called after. this method is called irrespective of exception. it is called always.");
	}

	@AfterReturning("execution(public * com.telusko.springmvcboot.restwebservice.AlienController.getAliens())")
	public void logAfterReturnnig() {

		LOGGER.info(
				"get alien method after returning called. this method is called when there is successfully execution.");
	}

	@AfterThrowing("execution(public * com.telusko.springmvcboot.restwebservice.AlienController.getAliens())")
	public void logAfterThrowing() {

		LOGGER.info(
				"get alien method called after throwing. this method is called when there is an exception.");
	}
}
