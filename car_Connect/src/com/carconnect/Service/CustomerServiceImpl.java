package com.carconnect.Service;

import java.sql.SQLException;
import java.util.List;

import com.carconnect.dao.CustomerDAOImpl;
import com.carconnect.dao.ICustomerDAO;
import com.carconnect.entity.*;
import com.carconnect.exception.*;

public class CustomerServiceImpl implements ICustomerService {

	private ICustomerDAO iCustomerDAO = new CustomerDAOImpl();;
	
	public CustomerServiceImpl() {
		super();
	}

	@Override
	public int addCustomer(Customer customer) {
		int result = 0;
		try {
			result = iCustomerDAO.addCustomer(customer);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		}catch(SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		}
		return result;
	}

	@Override
	public int updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCustomer(int customerId) {
		int result = 0;
		try {
			result = iCustomerDAO.deleteCustomer(customerId);
			}catch (ClassNotFoundException cnfe) {
				System.out.println("Looks like JDBC driver is NOT loaded.");
			}catch(SQLException se) {
				System.out.println("Either url, username or password is wrong or duplicate record");
			}catch(CustomerNotFoundException cnfe) {
				System.out.println(cnfe.getMessage());
			}
		
		return result;
	}

	@Override
	public Customer viewCustomer(int customerId) {
		Customer customer = null;
		
		try {
			customer = iCustomerDAO.viewCustomer(customerId);
			}catch (ClassNotFoundException cnfe) {
				System.out.println("Looks like JDBC driver is NOT loaded.");
			}catch(SQLException se) {
				System.out.println("Either url, username or password is wrong or duplicate record");
			}catch(CustomerNotFoundException cnfe) {
				System.out.println(cnfe.getMessage());
			}
		
		return customer;
	}

	@Override
	public List<Customer> viewCustomers() {
		List<Customer>customerList = null;
		
		try {
		customerList = iCustomerDAO.viewCustomers();
		}catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		}catch(SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		}catch(CustomerNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
		return customerList;
	}

}
