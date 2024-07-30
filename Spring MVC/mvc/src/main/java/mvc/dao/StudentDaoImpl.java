package mvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mvc.model.entities.Student;

@Repository
public class StudentDaoImpl implements StudentDao{
	
	/*
	 * Injecting Dependencies
	 */
	@Autowired
	private SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public int insert(Student student) {
		int rowsAffected=(int) factory.getCurrentSession().save(student);
		System.out.println("Rows affected -"+rowsAffected);
		return rowsAffected;
	}

	
}
