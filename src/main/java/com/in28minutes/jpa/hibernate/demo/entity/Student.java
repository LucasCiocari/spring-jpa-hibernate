package com.in28minutes.jpa.hibernate.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
//@Table(name="CourseDetails") //DEFINE THE NAME OF THE TABLE WHEN THE TABLE AND THE ENTITY NAME DIFFERS -- Needs to map only once
public class Student {
	
	@Id // Primary Key
	@GeneratedValue
	private Long id;
	
	@Column(/*name="fullname",*/ nullable = false) //name has the same idea of @Table -- nullable -> can/not be null -- @Unique, @length to string fields @precision and @scale to numeric fields. 
	private String name;
	
	
	@OneToOne(fetch=FetchType.LAZY)
	private Passport passport;
	
	protected Student() {}
	
	public Student(String name) {
		this.name = name;
	}

	
	
	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
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
		return String.format("Student [%s]", name);
	}
}
