package com.spring.orm.service;

import java.util.List;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

import jakarta.transaction.Transactional;

@Transactional
public class StudentService {

	private StudentDao studentdao;

	public StudentDao getStudentdao() {
		return studentdao;
	}

	public void setStudentdao(StudentDao studentdao) {
		this.studentdao = studentdao;
	}
	
	public int insertOperation(Student student) {
		return studentdao.insert(student);
	}
	
	public int updateData(Student student) {
		return studentdao.update(student);
	}
	
	public Student fetchbyId(int id) {
		return studentdao.fetchbyId(id);
	}
	
	public List<Student> fetchAll(){
		return studentdao.fetchAll();
	}
	
	public int deletedata(int id) {
		return studentdao.delete(id);
	}
}
