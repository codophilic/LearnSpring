package com.spring.orm.dao;


import java.util.List;

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
	
	public int update(Student student) {
		sessionFactory.getCurrentSession().update(student);
		return 1;
	}
	
	public List<Student> fetchAll(){
		return sessionFactory.getCurrentSession().createQuery("from Student", Student.class).list();
	}
	
	public Student fetchbyId(int id) {
        return sessionFactory.getCurrentSession().get(Student.class, id);

	}
	
	public int delete(int id) {
		Student entity = sessionFactory.getCurrentSession().get(Student.class, id);
        if (entity != null) {
            sessionFactory.getCurrentSession().delete(entity);
            return 1;
        }
        return 0;
	}
}
