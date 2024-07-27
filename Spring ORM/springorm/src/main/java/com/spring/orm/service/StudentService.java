package com.spring.orm.service;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

import jakarta.transaction.Transactional;

public class StudentService {

	private StudentDao studentdao;

	public StudentDao getStudentdao() {
		return studentdao;
	}

	public void setStudentdao(StudentDao studentdao) {
		this.studentdao = studentdao;
	}
	
	@Transactional
	public int insertOperation(Student student) {
		return studentdao.insert(student);
	}
}
