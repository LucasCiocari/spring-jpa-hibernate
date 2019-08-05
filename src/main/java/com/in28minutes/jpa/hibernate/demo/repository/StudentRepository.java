package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	
	@Autowired
	EntityManager em;
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}
	
	public Student save(Student student) {
		if(student.getId()==null) {
			em.persist(student);
		} else {
			em.merge(student);
		}
		
		return student; 
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);
		
		Student student = new Student("Mike");
		student.setPassport(passport);
		em.persist(student);
		
	}
	
//	public void playWithEntityManager() { //Transaction -- em keeps track of all changes
//		Student student = new Student("Web Services in 100 Steps");
//		em.persist(student); // create a new entity
//		Student student2 = new Student("Angular JS in 100 Steps");
//		em.persist(student2); // create a new entity
//		em.flush(); // changes up to that point are sent to the database
////		em.clear(); // detach for all things tracked by the EM
////		em.detach(student2); //changes to student2 are not tracked by the entity manager anymore
//		
//		
//		
//		
//		
//		student.setName("Web Services in 100 Steps - Updated");
////		em.flush(); // changes up to that point are sent to the database
//		student2.setName("Angular JS in 100 Steps - Updated");
//		em.refresh(student);
//		em.flush(); // changes up to that point are sent to the database
//		
//	}
	
}
