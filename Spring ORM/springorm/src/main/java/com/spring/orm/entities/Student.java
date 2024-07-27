package com.spring.orm.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "student_information")
public class Student{

	@Id
	@Column(name = "student_id")
	private int rollnumber;
	
	@Column(name = "student_name")
	private String studentName;
	
	@Column(name = "student_city")
	private String studentCity;

	public int getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(int rollnumber) {
		this.rollnumber = rollnumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentCity() {
		return studentCity;
	}

	public void setStudentCity(String studentCity) {
		this.studentCity = studentCity;
	}
	
}
