package com.carconnect.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.carconnect.entity.Admin;

public class ResultSetFormatter {

	public static Admin toadmin(ResultSet resultset) throws SQLException {
		Admin admin = new Admin();

		admin.setAdminId(resultset.getInt("adminId"));
		admin.setFirstName(resultset.getString("firstName"));
		admin.setLastName(resultset.getString("lastName"));
		admin.setEmail(resultset.getString("email"));
		admin.setPhoneNumber(resultset.getString("phoneNumber"));
		admin.setUserName(resultset.getString("userName"));
		admin.setPassword(resultset.getString("password"));
		admin.setRole(resultset.getString("role"));
		admin.setJoinDate(resultset.getDate("joinDate").toLocalDate());

		return admin;
	}

}
