package org.bhavin.hibernate.demo;

import org.bhavin.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		//use the session object to save java object
		try {
			//create a student object
			System.out.println("Creating new student object");
			Student tempStudent = new Student("Paul", "Walker", "abc@gmail.com");
			
			//start transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving tempStudent object");
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done");
			
		}finally {
			factory.close();
		}
	}

}
