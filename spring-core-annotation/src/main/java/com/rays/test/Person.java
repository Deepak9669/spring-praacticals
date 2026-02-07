package com.rays.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // Person person = new Person();
public class Person {

	private String name;
	private String address;

	public String getName() {
		return name;
	}

//	@Value("ram")
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

//	@Value("indore")
	public void setAddress(String address) {
		this.address = address;
	}

}
