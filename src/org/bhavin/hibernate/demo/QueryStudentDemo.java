package org.bhavin.hibernate.demo;

import java.util.List;

import org.bhavin.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QueryStudentDemo {

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
			
			//start transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").list();
			//display students
			displayStudents(theStudents);
			
			// query students: last name
			theStudents = session.createQuery("from Student s where s.lastName='Gadot'").list();
			//display students
			System.out.println("\n\nStudents with last name condition");
			displayStudents(theStudents);
			
			// query students: last name or first name
			theStudents = session.createQuery("from Student s where s.lastName='Chan' or s.firstName='Donald'").list();
			System.out.println("\n\nStudents with last name and first name condition");
			displayStudents(theStudents);
			
			// query student: like clause
			theStudents = session.createQuery("from Student s where s.email like '%ck%'").list();
			System.out.println("\n\nStudents with like clause");
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
