package com.carconnect.Service;

import com.carconnect.entity.Vehicle;

public interface IVehicleService {
	
	public Vehicle getVehicleById(int vehicleId);
	public int registerVehicle(Vehicle vehicle);
	public int  updateVehicle(Vehicle vehicle);
	public int deleteVehicle(int vehicleId);
	public Vehicle getVehicleByMake(String vehicleMake);
}
