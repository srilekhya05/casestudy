package com.carconnect.dao;

import com.carconnect.Service.AdminServiceImpl;
import com.carconnect.Service.IAdminService;
import com.carconnect.entity.Admin;
import com.carconnect.exception.AdminNotFoundException;
import com.carconnect.exception.InvalidInputException;
import com.carconnect.util.DBUtil;
import com.carconnect.util.ResultSetFormatter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminImpldao implements IAdmindao {
	public AdminImpldao() {
		super();
	}

	Statement statement;
	ResultSet resultSet;

	@Override
	public Admin getAdminById(int adminId) throws AdminNotFoundException {
		Admin ad = new Admin();

		try {
			PreparedStatement preparedStatement;
			Connection connection = DBUtil.createConnection();
			preparedStatement = connection.prepareStatement("Select * From Admin Where adminId=?");
			preparedStatement.setInt(1, adminId);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				ad = ResultSetFormatter.toadmin(resultSet);
			} else {
				throw new AdminNotFoundException("Admin with Id" + adminId + "does not exist.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.closeConnection();
		return ad;
	}

	@Override
	public Admin getAdminByUsername(String username) throws AdminNotFoundException {
		Admin admin = new Admin();
		try {
			PreparedStatement preparedStatement;
			Connection connection = DBUtil.createConnection();
			preparedStatement = connection.prepareStatement("Select * From Admin Where username=?");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				admin = ResultSetFormatter.toadmin(resultSet);
			} else {
				throw new AdminNotFoundException("Admin with UserName " + username + " does not exist.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.closeConnection();
		return admin;
	}

	@Override
	public void registerAdmin(Admin adminData) throws InvalidInputException {
		try {
			PreparedStatement preparedStatement;
			Connection connection = DBUtil.createConnection();
			preparedStatement = connection.prepareStatement(
					"INSERT INTO Admin(firstName, lastName, email, phoneNumber, username, password, role, joinDate) VALUES (?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, adminData.getFirstName());
			preparedStatement.setString(2, adminData.getLastName());
			preparedStatement.setString(3, adminData.getEmail());
			preparedStatement.setString(4, adminData.getPhoneNumber());
			preparedStatement.setString(5, adminData.getUserName());
			preparedStatement.setString(6, adminData.getPassword());
			preparedStatement.setString(7, adminData.getRole());
			preparedStatement.setDate(8, Date.valueOf(adminData.getJoinDate()));
			int rows = preparedStatement.executeUpdate();

			if (rows > 0) {
				System.out.println("Admin with the given information is registered");
			} else {
				throw new InvalidInputException("You wrote wrong input");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.closeConnection();
	}

	@Override
	public void updateAdmin(Admin adminData) throws InvalidInputException, AdminNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtil.createConnection();
			String query = "UPDATE Admin SET firstName=?, lastName=?, email=?, phoneNumber=?, username=?, password=?, role=?, joinDate=? WHERE adminId=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, adminData.getFirstName());
			preparedStatement.setString(2, adminData.getLastName());
			preparedStatement.setString(3, adminData.getEmail());
			preparedStatement.setString(4, adminData.getPhoneNumber());
			preparedStatement.setString(5, adminData.getUserName());
			preparedStatement.setString(6, adminData.getPassword());
			preparedStatement.setString(7, adminData.getRole());
			preparedStatement.setDate(8, Date.valueOf(adminData.getJoinDate()));
			preparedStatement.setInt(9, adminData.getAdminId());

			int rows = preparedStatement.executeUpdate();

			if (rows > 0) {
				System.out.println("Admin with the given information is updated");
			} else {
				throw new AdminNotFoundException("Admin with Id " + adminData.getAdminId() + " does not exist.");
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Log or handle the exception appropriately
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace(); // Log or handle the exception appropriately
			}
		}
	}

	@Override
	public void deleteAdmin(int adminId) throws AdminNotFoundException {
		try {
			PreparedStatement preparedStatement;
			Connection connection = DBUtil.createConnection();
			preparedStatement = connection.prepareStatement("Delete From Admin Where adminID=?");
			preparedStatement.setInt(1, adminId);
			int rows = preparedStatement.executeUpdate();

			if (rows == 1) {
				System.out.println("Admin with id " + adminId + " is deleted");
			} else {
				throw new AdminNotFoundException("Admin with Id " + adminId + " does not exist.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.closeConnection();
	}

	@Override
	public String getPassword(int adminid) {

		try {
			PreparedStatement preparedStatement;
			Connection connection = DBUtil.createConnection();
			preparedStatement = connection.prepareStatement("Select password From Admin Where adminId=?");
			preparedStatement.setInt(1, adminid);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getString("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.closeConnection();
		return null;
	}

}
