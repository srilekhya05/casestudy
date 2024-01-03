package com.carconnect.Service;

import java.sql.SQLException;

import com.carconnect.dao.IVehicleDAO;
import com.carconnect.dao.VehicleDAOImpl;
import com.carconnect.entity.Vehicle;
import com.carconnect.exception.DatabaseConnectionException;
import com.carconnect.exception.InvalidInputException;
import com.carconnect.exception.VehicleNotFoundException;

public class VehicleServiceImpl implements IVehicleService {

	private IVehicleDAO iVehicleDAO;
	
	public VehicleServiceImpl() {
		iVehicleDAO = new VehicleDAOImpl();
	}

	@Override
	public Vehicle getVehicleById(int vehicleId) {
		// TODO Auto-generated method stub
		Vehicle vehicle = null;
		try {
			vehicle = iVehicleDAO.getVehicleById(vehicleId);
		}catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		}catch(DatabaseConnectionException dbe) {
			System.out.println("Database Connection Exception ");
		}catch(VehicleNotFoundException vnfe) {
			System.out.println("Vehicle Not Found ");
		}catch(SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record"+se);
		}
		return vehicle;
		
	}
	@Override
	public Vehicle getVehicleByMake(String vehicleMake) {
		// TODO Auto-generated method stub
		Vehicle vehicle = null;
		try {
			vehicle = iVehicleDAO.getVehicleByMake(vehicleMake);
		}catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		}catch(DatabaseConnectionException dbe) {
			System.out.println("Database Connection Exception ");
		}catch(VehicleNotFoundException vnfe) {
			System.out.println("Vehicle Not Found ");
		}catch(SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record"+se);
		}
		return vehicle;
		
	}
	@Override
	public int registerVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = iVehicleDAO.registerVehicle(vehicle);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Looks like JDBC driver is NOT loaded.");
		}catch(DatabaseConnectionException dbe) {
			System.out.println(" ");
		}catch(InvalidInputException inve) {
			System.out.println(" ");
		}catch(SQLException se) {
			System.out.println("Either url, username or password is wrong or duplicate record"+se);
		}
		return result;
		
	}
	@Override
	public int updateVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = iVehicleDAO.updateVehicle(vehicle);
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
		return result;
		
	}
	@Override
	public int deleteVehicle(int vehicleId)  {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = iVehicleDAO.deleteVehicle(vehicleId);
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
		return result;
		
	}




	
}
