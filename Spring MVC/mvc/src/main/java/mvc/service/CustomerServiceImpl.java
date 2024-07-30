package mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mvc.dao.CustomerDao;
import mvc.model.entities.Customer;


/**
 * Database changes are either committed (saved permanently) or rolled back (canceled) as a single unit via Transactional
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	/**
	 * Injecting Dependencies
	 */
	@Autowired
	private CustomerDao customerDao;

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public int insert(Customer cust) {
		return customerDao.insert(cust);
	}

	
}
