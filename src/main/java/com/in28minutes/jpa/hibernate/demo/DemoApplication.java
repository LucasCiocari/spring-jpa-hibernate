package com.in28minutes.jpa.hibernate.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.demo.repository.StudentRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private CourseRepository coursesRepository;

	@Autowired
	private StudentRepository studentRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		studentRepository.saveStudentWithPassport();
		
//		repository.playWithEntityManager();
		
//		logger.info("Course 10001 -> {}", repository.findById(10001L));
//		repository.deleteById(10001L);
//		repository.save(new Course("Microservices in 100 Steps."));
	}

}
