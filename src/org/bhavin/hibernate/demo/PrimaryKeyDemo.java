package org.bhavin.hibernate.demo;

import org.bhavin.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
	
	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create 3 student object
			System.out.println("Creating 3 student object...");
			Student demoStudent1 = new Student("Armin","Van Buran","armin@gmail.com");
			Student demoStudent2 = new Student("Gal","Gadot","gal@gmail.com");
			Student demoStudent3 = new Student("Jackie","Chan","Jackie@gmail.com");
			//start transaction
			session.beginTransaction();
			
			//save object
			System.out.println("Saving students object...");
			session.save(demoStudent1);
			session.save(demoStudent2);
			session.save(demoStudent3);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done");
			
		} finally {
			factory.close();
		}
		
	}
	
}
