package com.in28minutes.jpa.hibernate.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
//@Table(name="CourseDetails") //DEFINE THE NAME OF THE TABLE WHEN THE TABLE AND THE ENTITY NAME DIFFERS -- Needs to map only once
@NamedQueries(
	value = {
		@NamedQuery(name="query_get_all_courses", query="Select c from Course c"),
		@NamedQuery(name="query_get_100_step_courses", query="Select c from Course c where name like '%100 Steps'")
	}
)


public class Course {
	
	@Id // Primary Key
	@GeneratedValue
	private Long id;
	
	@Column(/*name="fullname",*/ nullable = false) //name has the same idea of @Table -- nullable -> can/not be null -- @Unique, @length to string fields @precision and @scale to numeric fields. 
	private String name;
	
	@UpdateTimestamp // HIBERNATE ANNOTATION
	private LocalDateTime lastUpdatedDate;
	@CreationTimestamp //HIBERNATE ANNOTATION
	private LocalDateTime createdDate;
	
	protected Course() {}
	
	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Course [" + name + "]";
	}
}
