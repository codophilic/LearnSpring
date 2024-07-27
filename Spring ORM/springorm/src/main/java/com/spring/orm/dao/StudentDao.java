package com.spring.orm.dao;


import org.hibernate.SessionFactory;

import com.spring.orm.entities.Student;

public class StudentDao{

	private SessionFactory sessionFactory;

	public SessionFactory getsessionFactory() {
		return sessionFactory;
	}

	public void setsessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public int insert(Student student) {
		Integer i=(Integer) sessionFactory.getCurrentSession().save(student);
		return i;
	}
}
