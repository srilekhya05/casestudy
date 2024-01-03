package com.carconnect.dao;

import static org.junit.Assert.assertTrue;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.carconnect.entity.Vehicle;
import com.carconnect.exception.DatabaseConnectionException;
import com.carconnect.exception.InvalidInputException;
import com.carconnect.exception.VehicleNotFoundException;

public class VehicleDAOImplTest {
	private IVehicleDAO vehicleDAO;

	@Before
	public void setUp() throws Exception {
		vehicleDAO = new VehicleDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
		vehicleDAO = null;
	}

	@Test
	public final void testGetVehicleById() {
		Vehicle vehicle = null;
		int vehicleId = 10;
		try {
			vehicle = vehicleDAO.getVehicleById(vehicleId);
		}catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		}catch(DatabaseConnectionException dbe) {
			System.out.println("Database Connection Exception ");
		}catch(VehicleNotFoundException vnfe) {
			System.out.println("Vehicle Not Found ");
		}catch(SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record"+se);
		}
		assertTrue(vehicle != null);
	}

	@Test
	public final void testGetVehicleByMake() {
		Vehicle vehicle = null;
		String vehicleMake = "fdd";
		try {
			vehicle = vehicleDAO.getVehicleByMake(vehicleMake);
		}catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		}catch(DatabaseConnectionException dbe) {
			System.out.println("Database Connection Exception ");
		}catch(VehicleNotFoundException vnfe) {
			System.out.println("Vehicle Not Found ");
		}catch(SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record"+se);
		}
		assertTrue(vehicle != null);
	}

	@Test
	public final void testRegisterVehicle() {
		int result = 0;
		
		Vehicle vehicle = new Vehicle("Mustang", "Ford", 1990, "Black", "JKL101", false, 500.00);
		try {
			result = vehicleDAO.registerVehicle(vehicle);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		}catch(DatabaseConnectionException dbe) {
			System.out.println(" ");
		}catch(InvalidInputException inve) {
			System.out.println(" ");
		}catch(SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record"+se);
		}
		assertTrue(result == 1);
	}

	@Test
	public final void testUpdateVehicle() {
		int result = 0;
		
		Vehicle vehicle = new Vehicle("Mustang", "Ford", 1990, "Black", "jklmn101", true, 500.00);
		vehicle.setVehicleID(11);
		try {
			result = vehicleDAO.updateVehicle(vehicle);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		}catch(DatabaseConnectionException dbe) {
			System.out.println(" ");
		}catch(InvalidInputException inve) {
			System.out.println("Inavlid Input");
		}catch(VehicleNotFoundException vne) {
			System.out.println("Vehicle NOT Found");
		}catch(SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record"+se);
		}
		assertTrue(result == 1);
	}

	@Test
	public final void testDeleteVehicle() {
		int result = 0;
		int vehicleId = 6;
		try {
			result = vehicleDAO.deleteVehicle(vehicleId);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		}catch(DatabaseConnectionException dbe) {
			System.out.println(" ");
		}catch(InvalidInputException inve) {
			System.out.println("Inavlid Input");
		}catch(VehicleNotFoundException vne) {
			System.out.println("Vehicle NOT Found");
		}catch(SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record"+se);
		}
		assertTrue(result == 1);
	}

}
