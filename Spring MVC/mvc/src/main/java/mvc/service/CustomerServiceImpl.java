package mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mvc.model.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Override
	public int insert(Customer cust) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
