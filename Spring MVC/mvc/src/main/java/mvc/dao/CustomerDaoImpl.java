package mvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import mvc.model.Customer;

public class CustomerDaoImpl implements CustomerDao{
	
	/*
	 * Injecting Dependencies
	 */
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int insert(Customer cust) {
		int rowsAffected=(int) sessionFactory.openSession().save(cust);
		return rowsAffected;
	}

	
}
