package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;



@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)

public class NativeQueriesTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;
	
	
	@Test
	public void nativeQueries_basic() {
		
		Query query = em.createNativeQuery("SELECT * FROM COURSE");
		List resultList = query.getResultList();
		
		logger.info("SELECT * FROM COURSE -> {}", resultList);
	}

	
	@Test
	public void nativeQueries_typed() {
		
		Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
		List<Course> resultList = query.getResultList();
		
		logger.info("SELECT * FROM COURSE -> {}", resultList);
	}

	@Test
	public void nativeQueries_where() {
		
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id=?", Course.class);
		query.setParameter(1, 10001L);
		List<Course> resultList = query.getResultList();
		
		
		logger.info("SELECT * FROM COURSE -> {}", resultList);
	}
	
	@Test
	public void nativeQueries_namedParameter() {
		
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id=:id", Course.class);
		query.setParameter("id", 10001L);
		List<Course> resultList = query.getResultList();
		
		
		logger.info("SELECT * FROM COURSE -> {}", resultList);
	}

	@Test
	@Transactional
	public void nativeQueries_toUpdate() {
		
		Query query = em.createNativeQuery("UPDATE COURSE set last_updated_date=sysdate()", Course.class);
		int noOfRowsUpdated = query.executeUpdate();
		
	}
}

