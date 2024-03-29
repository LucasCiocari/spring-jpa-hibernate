package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

@Repository
@Transactional
public class CourseRepository {
	
	@Autowired
	EntityManager em;
	
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	
	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}
	
	public Course save(Course course) {
		if(course.getId()==null) {
			em.persist(course);
		} else {
			em.merge(course);
		}
		
		return course; 
	}
	
	public void playWithEntityManager() {
		Course course = new Course("Web Services in 100 Steps");
		em.persist(course);
		
		Course course2 = findById(10001L);
		course2.setName("JPA in 50 Steps - Updated");
		
	}
	
//	public void playWithEntityManager() { //Transaction -- em keeps track of all changes
//		Course course = new Course("Web Services in 100 Steps");
//		em.persist(course); // create a new entity
//		Course course2 = new Course("Angular JS in 100 Steps");
//		em.persist(course2); // create a new entity
//		em.flush(); // changes up to that point are sent to the database
////		em.clear(); // detach for all things tracked by the EM
////		em.detach(course2); //changes to course2 are not tracked by the entity manager anymore
//		
//		
//		
//		
//		
//		course.setName("Web Services in 100 Steps - Updated");
////		em.flush(); // changes up to that point are sent to the database
//		course2.setName("Angular JS in 100 Steps - Updated");
//		em.refresh(course);
//		em.flush(); // changes up to that point are sent to the database
//		
//	}
	
}
