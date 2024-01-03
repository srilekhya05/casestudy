package com.carconnect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.carconnect.entity.Vehicle;
import com.carconnect.exception.DatabaseConnectionException;
import com.carconnect.exception.InvalidInputException;
import com.carconnect.exception.VehicleNotFoundException;
import com.carconnect.util.DBUtil;

public class VehicleDAOImpl implements IVehicleDAO {

	private static Connection connVehicle;
	
	@Override
	public Vehicle getVehicleById(int vehicleId) throws VehicleNotFoundException, DatabaseConnectionException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Vehicle vehicle = null;

		int result = 0;
		int year = 0;
		String model = null;
		String make = null;
		String registrationNumber = null;
		String color = null;
		boolean availability = false;
		double dailyRate = 0;

		connVehicle = DBUtil.createConnection();

		String query = "SELECT * FROM vehicle WHERE vehicleId = ?";

		PreparedStatement prepareStVehicle = connVehicle.prepareStatement(query);

		prepareStVehicle.setInt(1, vehicleId);

		ResultSet rsVehicle = prepareStVehicle.executeQuery();

		while (rsVehicle.next()) {// Till there are further records.
			vehicleId = rsVehicle.getInt("vehicleId");
			model = rsVehicle.getString("model");
			make = rsVehicle.getString("make");
			year = rsVehicle.getInt("year");
			color = rsVehicle.getString("color");
			registrationNumber = rsVehicle.getString("registrationNumber");
			availability = rsVehicle.getBoolean("availability");
			dailyRate = rsVehicle.getDouble("dailyRate");
			
			vehicle = new Vehicle(model, make, year, color, registrationNumber, availability, dailyRate);
			vehicle.setVehicleID(vehicleId);
		}

		DBUtil.closeConnection();

		if (vehicle == null) {
			throw new VehicleNotFoundException("No Vehicle Found");
		}

		return vehicle;
	}
	

	@Override
	public Vehicle getVehicleByMake(String vehicleMake) throws DatabaseConnectionException, ClassNotFoundException, SQLException, VehicleNotFoundException {
		// TODO Auto-generated method stub
		Vehicle vehicle = null;
		int vehicleId = 0;
		int result = 0;
		int year = 0;
		String model = null;
		String make = null;
		String registrationNumber = null;
		String color = null;
		boolean availability = false;
		double dailyRate = 0;

		connVehicle = DBUtil.createConnection();

		String query = "SELECT * FROM vehicle WHERE make = ?";

		PreparedStatement prepareStVehicle = connVehicle.prepareStatement(query);

		prepareStVehicle.setString(1, vehicleMake);

		ResultSet rsVehicle = prepareStVehicle.executeQuery();

		while (rsVehicle.next()) {// Till there are further records.
			vehicleId = rsVehicle.getInt("vehicleId");
			model = rsVehicle.getString("model");
			make = rsVehicle.getString("make");
			year = rsVehicle.getInt("year");
			color = rsVehicle.getString("color");
			registrationNumber = rsVehicle.getString("registrationNumber");
			availability = rsVehicle.getBoolean("availability");
			dailyRate = rsVehicle.getDouble("dailyRate");
			
			vehicle = new Vehicle(model, make, year, color, registrationNumber, availability, dailyRate);
			vehicle.setVehicleID(vehicleId);
		}

		DBUtil.closeConnection();

		if (vehicle == null) {
			throw new VehicleNotFoundException("No Vehicle Found");
		}

		return vehicle;
		
	}

	@Override
	public int registerVehicle(Vehicle vehicle) throws InvalidInputException, DatabaseConnectionException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		connVehicle = DBUtil.createConnection();
		String query = "INSERT INTO Vehicle(model, make, year, color, registrationNumber, availability, dailyRate) VALUES(?,?,?,?,?,?,?)";
		
		PreparedStatement prepareStVehicle = connVehicle.prepareStatement(query);
	
		prepareStVehicle.setString(1, vehicle.getModel());
		prepareStVehicle.setString(2, vehicle.getMake());
		prepareStVehicle.setInt(3, vehicle.getYear());
		prepareStVehicle.setString(4, vehicle.getColor());
		prepareStVehicle.setString(5, vehicle.getRegistrationNumber());
		prepareStVehicle.setBoolean(6, vehicle.isAvailability());
		prepareStVehicle.setDouble(7, vehicle.getDailyRate());
		
		//Statement statement = connCustomer.createStatement();
		int result = prepareStVehicle.executeUpdate();
		DBUtil.closeConnection();
		return result;

	}
	
	@Override
	public int updateVehicle(Vehicle vehicle)
			throws VehicleNotFoundException, InvalidInputException, DatabaseConnectionException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		connVehicle = DBUtil.createConnection();
		String query = "UPDATE Vehicle SET model=?, make=?, year=?, color=?, registrationNumber=?, availability=?, dailyRate=? WHERE vehicleID =?";
		
		PreparedStatement prepareStVehicle = connVehicle.prepareStatement(query);
		prepareStVehicle.setString(1, vehicle.getModel());
		prepareStVehicle.setString(2, vehicle.getMake());
		prepareStVehicle.setInt(3, vehicle.getYear());
		prepareStVehicle.setString(4, vehicle.getColor());
		prepareStVehicle.setString(5, vehicle.getRegistrationNumber());
		prepareStVehicle.setBoolean(6, vehicle.isAvailability());
		prepareStVehicle.setDouble(7, vehicle.getDailyRate());
		prepareStVehicle.setInt(8, vehicle.getVehicleID());
		
		int result = prepareStVehicle.executeUpdate();
		
		DBUtil.closeConnection();
		return result;

	}

	@Override
	public int deleteVehicle(int vehicleId) throws VehicleNotFoundException, DatabaseConnectionException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Vehicle vehicle = null;
		int result = 0;
		int year = 0;
		String model = null;
		String make = null;
		String registrationNumber = null;
		String color = null;
		boolean availability = false;
		double dailyRate = 0;
		connVehicle = DBUtil.createConnection();
		String queryCheck = "SELECT * FROM vehicle WHERE vehicleId = ?";
		String queryDelete = "DELETE FROM vehicle WHERE vehicleId = ?";
		
		PreparedStatement prepareStVehicle = connVehicle.prepareStatement(queryCheck);
		PreparedStatement prepareStVehicleDelete = connVehicle.prepareStatement(queryDelete);
		
		prepareStVehicle.setInt(1, vehicleId);
		prepareStVehicleDelete.setInt(1, vehicleId);
		
		ResultSet rsVehicle = prepareStVehicle.executeQuery();
		
		while (rsVehicle.next()) {
			model = rsVehicle.getString("model");
			make = rsVehicle.getString("make");
			year = rsVehicle.getInt("year");
			color = rsVehicle.getString("color");
			registrationNumber = rsVehicle.getString("registrationNumber");
			availability = rsVehicle.getBoolean("availability");
			dailyRate = rsVehicle.getDouble("dailyRate");
			
			vehicle = new Vehicle(model, make, year, color, registrationNumber, availability, dailyRate);
			vehicle.setVehicleID(vehicleId);
		}
		if (vehicle == null) {
			throw new VehicleNotFoundException("No Vehicle Found");
		}else {
			result = prepareStVehicleDelete.executeUpdate();
		}

		DBUtil.closeConnection();
		
		return result;
	}


		

}
