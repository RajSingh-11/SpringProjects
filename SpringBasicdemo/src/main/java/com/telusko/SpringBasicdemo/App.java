package com.telusko.SpringBasicdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
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
	//	obj1.age = 15;
	//	System.out.println(obj1.age);

		/**
		 * NOTE: All the spring beans are singleton beans, means we get the same object
		 * every time, or it create an object once which refers to same memory location
		 * like here obj1 and obj2 referring to same memory location, but if the scope
		 * in spring.xml file is prototype, it creates different object every time.
		 */
		Alien obj2 = (Alien) factory.getBean("alien");
		obj2.code();
		//System.out.println(obj2.age);
		
		
		/** Setter Injection */
		Alien obj3 = (Alien) factory.getBean("alien");
		obj3.code();
		System.out.println(obj3.getAge());
	}
}
