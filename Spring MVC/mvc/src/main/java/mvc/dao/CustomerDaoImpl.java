package mvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mvc.model.entities.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	
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

	@Override
	public int insert(Customer cust) {
		int rowsAffected=(int) factory.openSession().save(cust);
		return rowsAffected;
	}

	
}
