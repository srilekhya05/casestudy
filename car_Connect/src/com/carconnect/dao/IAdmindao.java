package com.carconnect.dao;

import java.util.List;

import com.carconnect.entity.Admin;
import com.carconnect.exception.AdminNotFoundException;
import com.carconnect.exception.InvalidInputException;

public interface IAdmindao {
	Admin getAdminById(int adminId) throws AdminNotFoundException;

	Admin getAdminByUsername(String username) throws AdminNotFoundException;

	void registerAdmin(Admin adminData) throws InvalidInputException;

	void updateAdmin(Admin adminData) throws InvalidInputException, AdminNotFoundException;

	void deleteAdmin(int adminId) throws AdminNotFoundException;
	
	String getPassword(int adminid);
}
