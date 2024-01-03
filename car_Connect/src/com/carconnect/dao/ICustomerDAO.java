package com.carconnect.dao;

import java.sql.SQLException;
import java.util.List;

import com.carconnect.entity.*;
import com.carconnect.exception.*;

public interface ICustomerDAO {
	public int addCustomer(Customer customer) throws ClassNotFoundException, SQLException;
	public int updateCustomer(Customer customer) throws ClassNotFoundException, SQLException, CustomerNotFoundException;
	public int deleteCustomer(int customerId) throws ClassNotFoundException, SQLException, CustomerNotFoundException;
	public Customer viewCustomer(int customerId) throws ClassNotFoundException, SQLException, CustomerNotFoundException;
	public List<Customer>viewCustomers() throws ClassNotFoundException, SQLException, CustomerNotFoundException;
}
