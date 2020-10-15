package org.bhavin.hibernate.demo;

import org.bhavin.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		
		try {
			//using the session object to update database via java object
			int studentId = 1;
			
			//start transaction
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: "+studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student...");
			
			myStudent.setLastName("Burin");
		
			//commit transaction
			session.getTransaction().commit();
			
			// updating the database using query object
			
			session=factory.getCurrentSession();
			
			session.beginTransaction();
			
			System.out.println("\n\nUpdating student using query");
			
			session.createQuery("update Student set email='avb@gmail.com' where id="+studentId).executeUpdate();
			
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		}finally {
			factory.close();
		}
	}

}
