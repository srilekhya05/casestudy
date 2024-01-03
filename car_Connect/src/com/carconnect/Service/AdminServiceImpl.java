package com.carconnect.Service;

import java.util.Scanner;

import com.carconnect.dao.*;
import com.carconnect.entity.Admin;
import com.carconnect.exception.AdminNotFoundException;
import com.carconnect.exception.InvalidInputException;

public class AdminServiceImpl implements IAdminService {

	public AdminServiceImpl() {
		super();
	}

	IAdmindao adsrvcdb = new AdminImpldao();

	@Override
	public void getAdminById(int adminid) {

		try {
			Admin admin1 = adsrvcdb.getAdminById(adminid);
			System.out.println(admin1.toString());
		} catch (AdminNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void getAdminByUsername(String username) {

		try {
			Admin admin = adsrvcdb.getAdminByUsername(username);
			System.out.println(admin.toString());

		} catch (AdminNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void registerAdmin(Admin admin) {

		try {
			adsrvcdb.registerAdmin(admin);
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void updateAdmin(Admin admin) {
		try {
			adsrvcdb.updateAdmin(admin);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	@Override
	public void deleteAdmin(int adminid, String password) {
		try {
			if (Authenticate(adminid, password)) {
				adsrvcdb.deleteAdmin(adminid);

			} else {
				System.out.println("Given password or AdminId is wrong plese enter again");
			}
		} catch (AdminNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public boolean Authenticate(int adminid, String password) {
		String tmpPassword = adsrvcdb.getPassword(adminid);
		if (tmpPassword.equals(password)) {
			return true;
		} else {
			return false;
		}

	}

}
