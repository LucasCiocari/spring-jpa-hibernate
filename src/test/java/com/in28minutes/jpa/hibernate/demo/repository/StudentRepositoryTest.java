package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)

public class StudentRepositoryTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("Student -> {}", student);
		logger.info("Passport details are -> {}", student.getPassport());
	}
	
	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent() {
		Passport passport= em.find(Passport.class, 40001L);
		logger.info("Passport -> {}", passport);
		logger.info("student -> {}", passport.getStudent());
	}
	
	
	@Test
	@Transactional //Also creates a persistence context, em acesses the persistence context, if not defined, each call is its own transaction
	public void someTest() {
		//DB OP1 - Retrieve Student
		Student student = em.find(Student.class, 20001L);
		//Persistence Context (student)
		//DB OP2 - Retrieve Passport
		Passport passport = student.getPassport();
		//Persistence Context (student, passport)
		//DB OP3 - Update Passport
		passport.setNumber("E1234567");
		//Persistence Context (student, passport++)
		//DB OP4 - Update Student
		student.setName("Ranga Updated");
		//Persistence Context (student++, passport++)
	}
	
	
}
