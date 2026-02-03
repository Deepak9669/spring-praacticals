package com.rays.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class TestPerson {

	public static void main(String[] args) {

//		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("user.xml"));
		
		ApplicationContext factory  = new ClassPathXmlApplicationContext("user.xml");

		Person dto = (Person) factory.getBean("person");
		
		System.out.println(dto.getName());
		System.out.println(dto.getLogin());
		System.out.println(dto.getPassword());
		
		System.out.println("<---------------->");
		
		 dto = (Person) factory.getBean("person1");
			
			System.out.println(dto.getName());
			System.out.println(dto.getLogin());
			System.out.println(dto.getPassword());
	}

}
