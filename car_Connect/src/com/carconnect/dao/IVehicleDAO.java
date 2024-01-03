package com.carconnect.dao;

import java.sql.SQLException;

import com.carconnect.entity.Vehicle;
import com.carconnect.exception.DatabaseConnectionException;
import com.carconnect.exception.InvalidInputException;
import com.carconnect.exception.VehicleNotFoundException;

public interface IVehicleDAO {
	
	public Vehicle getVehicleById(int vehicleId) throws VehicleNotFoundException, DatabaseConnectionException, ClassNotFoundException, SQLException;
    public Vehicle getVehicleByMake(String vehicleMake) throws DatabaseConnectionException, ClassNotFoundException, SQLException, VehicleNotFoundException;
    public int registerVehicle(Vehicle vehicle) throws InvalidInputException, DatabaseConnectionException, ClassNotFoundException, SQLException;
    public int updateVehicle(Vehicle vehicle) throws VehicleNotFoundException, InvalidInputException, DatabaseConnectionException, ClassNotFoundException, SQLException;
    public int deleteVehicle(int vehicleId) throws VehicleNotFoundException, DatabaseConnectionException, InvalidInputException,ClassNotFoundException, SQLException ;
}
