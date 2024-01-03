package com.carconnect.Service;

import java.util.List;

import com.carconnect.entity.*;

public interface ICustomerService {
	public int addCustomer(Customer customer);
	public int updateCustomer(Customer customer);
	public int deleteCustomer(int customerId);
	public Customer viewCustomer(int customerId);
	public List<Customer>viewCustomers();
}
