package com.carconnect.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.carconnect.entity.*;

import com.carconnect.exception.*;


public class CustomerDaoTest {
     ICustomerDAO customerdao;
	@Before
	public void setUp() throws Exception {
		customerdao=new CustomerDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
		customerdao=null;
	}

	@Test
	public void testAddCustomer() {
		Customer customer=new Customer("Rahul", "raj", "rahul@gmail.com", "9548612357", "Mumbai,India",
				"pass", "rahulraj", "2022-06-18");
		int result = 0;
		try {
			result = customerdao.addCustomer(customer);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		}catch(SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		}
		assertTrue(result!=0);
	}
	
	@Test
	public void testdeleteCustomer() {
		int result = 0;
		try {
			result = customerdao.deleteCustomer(1);
			}catch (ClassNotFoundException cnfe) {
				System.out.println("Looks like JDBC driver is NOT loaded.");
			}catch(SQLException se) {
				System.out.println("Either url, username or password is wrong or duplicate record");
			}catch(CustomerNotFoundException cnfe) {
				System.out.println(cnfe.getMessage());
			}
		
		assertTrue(result!=0);
	}
	@Test
	public void testviewCustomer() {
		Customer customer = null;
		
		try {
			customer = customerdao.viewCustomer(2);
			}catch (ClassNotFoundException cnfe) {
				System.out.println("Looks like JDBC driver is NOT loaded.");
			}catch(SQLException se) {
				System.out.println("Either url, username or password is wrong or duplicate record");
			}catch(CustomerNotFoundException cnfe) {
				System.out.println(cnfe.getMessage());
			}
		
		assertTrue(customer!=null);
	}

	@Test
	public void testviewCustomers() {
		List<Customer>customerList = null;
		
		try {
		customerList = customerdao.viewCustomers();
		}catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		}catch(SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record");
		}catch(CustomerNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
		assertTrue(customerList!=null);
	}
	
}
