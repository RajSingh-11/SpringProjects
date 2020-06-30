package com.telusko.SpringBasicdemo;

public class Alien {

	private int age;
	private Computer com;
	// Autowire here is done byName which is com, or byType which is Computer.

	Alien() {
		System.out.println("Alien object created.");
	}

	public Alien(int age, Computer laptop) {
		System.out.println("Parameterized Constructor Called..");
		this.age = age;
		this.com = laptop;
	}

	public Alien(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	/**
	 * This method is called, when we have property tag in inside bean tag in
	 * spring.xml file because spring framework sets the property by calling the
	 * setter method, after creating the object. if we change the method name then,
	 * we should change the property name accordingly, otherwise spring framework
	 * will not recognize it.
	 */
	public void setAge(int age) {
		System.out.println("age assigned ");
		this.age = age;
	}

	public void code() {
		System.out.println("I am coding...");
		com.compile();
	}

	public Computer getCom() {
		return com;
	}

	public void setCom(Computer com) {
		System.out.println("set computer type");
		this.com = com;
	}

}
