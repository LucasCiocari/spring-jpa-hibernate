package com.in28minutes.jpa.hibernate.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
//@Table(name="CourseDetails") //DEFINE THE NAME OF THE TABLE WHEN THE TABLE AND THE ENTITY NAME DIFFERS -- Needs to map only once
public class Passport {
	
	@Id // Primary Key
	@GeneratedValue
	private Long id;
	
	@Column(/*name="fullname",*/ nullable = false) //name has the same idea of @Table -- nullable -> can/not be null -- @Unique, @length to string fields @precision and @scale to numeric fields. 
	private String number;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy="passport") // mappedBy -> guarantees the student id column is not created and that student is the owning side of relationship
	private Student student; 
	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	protected Passport() {}
	
	public Passport(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Passport [%s]", number);
	}
}
