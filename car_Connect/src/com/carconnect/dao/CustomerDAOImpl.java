package com.carconnect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carconnect.entity.*;
import com.carconnect.exception.*;
import com.carconnect.util.*;

public class CustomerDAOImpl implements ICustomerDAO {
	private static Connection connCustomer;

	@Override
	public int addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
		connCustomer = DBUtil.createConnection();
		String query = "INSERT INTO customer(firstname, lastname, email, phoneNumber,address,username,password,registrationDate) " + "VALUES(?,?,?,?,?,?,?,?)";

		PreparedStatement prepareStCustomer = connCustomer.prepareStatement(query);
		prepareStCustomer.setString(1, customer.getFirstName());
		prepareStCustomer.setString(2, customer.getLastName());
		prepareStCustomer.setString(3, customer.getEmail());
		prepareStCustomer.setString(4, customer.getPhoneNumber());
		prepareStCustomer.setString(5, customer.getAddress());
		prepareStCustomer.setString(6, customer.getUserName());
		prepareStCustomer.setString(7, customer.getPassword());
		prepareStCustomer.setString(8, customer.getRegistrationDate());

		int result = prepareStCustomer.executeUpdate();

		DBUtil.closeConnection();
		return result;
	}

	@Override
	public int updateCustomer(Customer customer)
			throws ClassNotFoundException, SQLException, CustomerNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCustomer(int customerId) throws ClassNotFoundException, SQLException, CustomerNotFoundException {
		Customer customer = null;

		String firstName = null;
		String lastName = null;
		String email = null;
		String phoneNumber = null;
		String address = null;
		String username = null;
		String password = null;
		String registrationDate = null;

		int result = 0;
		
		connCustomer = DBUtil.createConnection();

		String queryCheck = "SELECT * FROM customer WHERE customer_id = ?";
		String queryDelete = "DELETE FROM customer WHERE customer_id = ?";
		
		PreparedStatement prepareStCustomer = connCustomer.prepareStatement(queryCheck);
		PreparedStatement prepareCustomerDelete = connCustomer.prepareStatement(queryDelete);
		
		prepareStCustomer.setInt(1, customerId);
		prepareCustomerDelete.setInt(1, customerId);
		
		ResultSet rsCustomer = prepareStCustomer.executeQuery();

		while (rsCustomer.next()) {// Till there are further records.
			customerId = rsCustomer.getInt("customer_id");
			firstName = rsCustomer.getString("firstname");
			lastName = rsCustomer.getString("lastname");
			email = rsCustomer.getString("email");
			phoneNumber = rsCustomer.getString("phoneNumber");
			address = rsCustomer.getString("address");
			username=rsCustomer.getString("username");
			password=rsCustomer.getString("password");
			registrationDate=rsCustomer.getString("registrationDate");
			

			
			customer = new Customer(firstName, lastName, email, phoneNumber, address, username, password, registrationDate);
			customer.setCustomerId(customerId);
		}

		if (customer == null) {
			throw new CustomerNotFoundException("No Customer Found");
		}else {
			result = prepareCustomerDelete.executeUpdate();
		}

		DBUtil.closeConnection();
		
		return result;
	}

	@Override
	public Customer viewCustomer(int customerId)
			throws ClassNotFoundException, SQLException, CustomerNotFoundException {
		Customer customer = null;

		String firstName = null;
		String lastName = null;
		String email = null;
		String phoneNumber = null;
		String address = null;
		String username = null;
		String password = null;
		String registrationDate = null;

		connCustomer = DBUtil.createConnection();

		String query = "SELECT * FROM customer WHERE customer_id = ?";

		PreparedStatement prepareStCustomer = connCustomer.prepareStatement(query);

		prepareStCustomer.setInt(1, customerId);

		ResultSet rsCustomer = prepareStCustomer.executeQuery();

		while (rsCustomer.next()) {// Till there are further records.
			customerId = rsCustomer.getInt("customer_id");
			firstName = rsCustomer.getString("firstname");
			lastName = rsCustomer.getString("lastname");
			email = rsCustomer.getString("email");
			phoneNumber = rsCustomer.getString("phoneNumber");
			address = rsCustomer.getString("address");
			username=rsCustomer.getString("username");
			password=rsCustomer.getString("password");
			registrationDate=rsCustomer.getString("registrationDate");

			customer = new Customer(firstName, lastName, email, phoneNumber, address, username, password, registrationDate);
			customer.setCustomerId(customerId);
		}

		DBUtil.closeConnection();

		if (customer == null) {
			throw new CustomerNotFoundException("No Customer Found");
		}

		return customer;
	}

	@Override
	public List<Customer> viewCustomers() throws ClassNotFoundException, SQLException, CustomerNotFoundException {
		List<Customer> customers = new ArrayList<>();
		Customer customer = null;

		int customerId = 0;
		String firstName = null;
		String lastName = null;
		String email = null;
		String phoneNumber = null;
		String address = null;
		String username = null;
		String password = null;
		String registrationDate = null;

		connCustomer = DBUtil.createConnection();

		String query = "SELECT * FROM customer";

		PreparedStatement prepareStCustomer = connCustomer.prepareStatement(query);

		ResultSet rsCustomer = prepareStCustomer.executeQuery();

		while (rsCustomer.next()) {// Till there are further records.
			customerId = rsCustomer.getInt("customer_id");
			firstName = rsCustomer.getString("firstname");
			lastName = rsCustomer.getString("lastname");
			email = rsCustomer.getString("email");
			phoneNumber = rsCustomer.getString("phoneNumber");
			address = rsCustomer.getString("address");
			username=rsCustomer.getString("username");
			password=rsCustomer.getString("password");
			registrationDate=rsCustomer.getString("registrationDate");

			customer = customer = new Customer(firstName, lastName, email, phoneNumber, address, username, password, registrationDate);
			customer.setCustomerId(customerId);

			customers.add(customer);
		}
		DBUtil.closeConnection();

		if (customers.size() == 0) {
			throw new CustomerNotFoundException("No Customer Found");
		}

		return customers;
	}

}
