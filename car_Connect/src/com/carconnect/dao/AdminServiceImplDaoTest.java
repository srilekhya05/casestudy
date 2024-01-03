package com.carconnect.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import com.carconnect.Service.AdminServiceImpl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.carconnect.dao.AdminImpldao;

import com.carconnect.entity.Admin;
import com.carconnect.exception.AdminNotFoundException;
import com.carconnect.exception.InvalidInputException;

public class AdminServiceImplDaoTest {
	private AdminImpldao AdminServicedao;

	@Before
	public void setUp() throws Exception {
		AdminServicedao = new AdminImpldao();
	}

	@After
	public void tearDown() throws Exception {
		AdminServicedao = null;
	}

	@Test
	public void testGetAdminById() {
		Admin admin = null;
		int adminid = 3;
		try {
			admin = AdminServicedao.getAdminById(adminid);
			System.out.println("get admin id done");
		} catch (AdminNotFoundException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(admin != null);
	}

	@Test
	public void testGetAdminByUsername() {
		Admin admin = null;
		String username = "jui";
		try {
			admin = AdminServicedao.getAdminByUsername(username);
			System.out.println("get admin username done");
		} catch (AdminNotFoundException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(admin != null);
	}

	@Test
	public void testRegisterAdmin() {
		LocalDate date = LocalDate.of(2022, 05, 15);
		Admin admin = new Admin("Hemu", "negi", "hemu@gmail.com", "9845671845", "hemn", "pass", "Admin", date);
		int result = 0;
		try {
			AdminServicedao.registerAdmin(admin);
			result = 1;

		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(result != 0);
	}

	@Test
	public void testUpdateAdmin() {
		LocalDate date = LocalDate.of(2022, 05, 18);
		int result = 0;
		Admin admin1 = new Admin(3, "karti", "negi", "kartik@gmail.com", "9845671845", "kartik", "pass", "Admin",
				date);
		try {
			AdminServicedao.updateAdmin(admin1);
			result = 1;
		} catch (Exception e) {
			e.getMessage();
		}
		assertTrue(result != 0);
	}

	@Test
	public void testDeleteAdmin() {

		int adminid = 5;
		int result = 0;
		try {
			AdminServicedao.deleteAdmin(adminid);
			result = 1;
		} catch (AdminNotFoundException e) {
			System.out.println(e.getMessage());
		}
		assertTrue(result != 0);
	}

}
