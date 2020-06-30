package com.telusko.SpringBasicdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		/**
		 * This is old way of creating bean factory
		 */
		// BeanFactory factory= new XmlBeanFactory(new
		// FileSystemResource("spring.xml"));

		ApplicationContext factory = new ClassPathXmlApplicationContext("spring.xml");
		// here the alien is id in spring.xml
		Alien obj1 = (Alien) factory.getBean("alien");
		obj1.code();
		System.out.println(obj1.getAge());

	}
}
