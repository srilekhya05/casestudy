package com.carconnect.dao;

import com.carconnect.entity.*;
import com.carconnect.exception.*;

import com.carconnect.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;

public class ReservationServicedao implements IReservationServicedao {
    private static final String GET_RESERVATION_BY_ID_QUERY = "SELECT * FROM Reservation WHERE ReservationID = ?";
    private static final String GET_RESERVATIONS_BY_CUSTOMER_ID_QUERY = "SELECT * FROM Reservation WHERE CustomerID = ?";
    private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET CustomerID = ?, VehicleID = ?, " +
            "StartDate = ?, EndDate = ?, TotalCost = ?, Status = ? WHERE ReservationID = ?";
    private static final String CANCEL_RESERVATION_QUERY = "DELETE FROM Reservation WHERE ReservationID = ?";
    private static Connection connection;
    
    public ReservationServicedao(){
        
            connection = DBUtil.createConnection();
          
    }

    @Override
    public Reservation getReservationById(int reservationId) throws DatabaseConnectionException, ClassNotFoundException {
        
        try (PreparedStatement statement = connection.prepareStatement(GET_RESERVATION_BY_ID_QUERY)) {

            
            statement.setInt(1, reservationId);

            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    
                    return mapResultSetToReservation(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error connecting to the database.");
        }

        return null; 
    }

    @Override
    public List<Reservation> getReservationsByCustomerId(int customerId) throws DatabaseConnectionException, ClassNotFoundException {
        
        List<Reservation> reservations = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(GET_RESERVATIONS_BY_CUSTOMER_ID_QUERY)) {

            
            statement.setInt(1, customerId);

            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Add each Reservation object to the list
                    reservations.add(mapResultSetToReservation(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error connecting to the database.");
        }

        return reservations;
    }

    
	@Override
    public void createReservation(Reservation reservation) throws DatabaseConnectionException, ClassNotFoundException, ReservationException {
    	
    	 
        if (isVehicleAlreadyReserved(reservation.getVehicleID(), reservation.getStartDate(), reservation.getEndDate())) {
            throw new ReservationException("Reservation conflicts with existing reservations.");
        }
        
        try (PreparedStatement statement = connection.prepareStatement(CREATE_RESERVATION_QUERY)) {

            
            statement.setInt(1, reservation.getReservationID());
            statement.setInt(2, reservation.getCustomerID());
            statement.setInt(3, reservation.getVehicleID());
            statement.setDate(4, new java.sql.Date(reservation.getStartDate().getTime()));
            statement.setDate(5, new java.sql.Date(reservation.getEndDate().getTime()));
            statement.setDouble(6, reservation.getTotalCost());
            statement.setString(7, reservation.getStatus());

            
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error connecting to the database.");
        }
    }

    @Override
    public void updateReservation(Reservation reservation) throws DatabaseConnectionException, ClassNotFoundException,ReservationException {
    	if (isVehicleAlreadyReserved(reservation.getVehicleID(), reservation.getStartDate(), reservation.getEndDate())) {
            throw new ReservationException("Updated reservation conflicts with existing reservations.");
        }
        
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_RESERVATION_QUERY)) {

            
            statement.setInt(1, reservation.getCustomerID());
            statement.setInt(2, reservation.getVehicleID());
            statement.setDate(3, new java.sql.Date(reservation.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(reservation.getEndDate().getTime()));
            statement.setDouble(5, reservation.getTotalCost());
            statement.setString(6, reservation.getStatus());
            statement.setInt(7, reservation.getReservationID());

            
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error connecting to the database.");
        }
    }

    @Override
    public void cancelReservation(int reservationId) throws DatabaseConnectionException, ClassNotFoundException {
        
        try ( PreparedStatement statement = connection.prepareStatement(CANCEL_RESERVATION_QUERY)) {

           
            statement.setInt(1, reservationId);

            
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error connecting to the database.");
        }
    }

    private Reservation mapResultSetToReservation(ResultSet resultSet) throws SQLException {
        
       Reservation rr=new Reservation(
                resultSet.getInt("ReservationID"),
                resultSet.getInt("CustomerID"),
                resultSet.getInt("VehicleID"),
                resultSet.getDate("StartDate"),
                resultSet.getDate("EndDate"),
                resultSet.getDouble("TotalCost"),
                resultSet.getString("Status")
        );
       return rr;
    }
    
    
   

    private boolean isVehicleAlreadyReserved(int vehicleId, Date startDate, Date endDate) throws DatabaseConnectionException, ClassNotFoundException {
        
        String checkReservationQuery = "SELECT COUNT(*) FROM Reservation " +
                "WHERE VehicleID = ? AND ((StartDate <= ? AND EndDate >= ?) OR (StartDate <= ? AND EndDate >= ?))";

        try (PreparedStatement statement = connection.prepareStatement(checkReservationQuery)) {

            statement.setInt(1, vehicleId);
            statement.setDate(2, new java.sql.Date(startDate.getTime()));
            statement.setDate(3, new java.sql.Date(endDate.getTime()));
            statement.setDate(4, new java.sql.Date(startDate.getTime()));
            statement.setDate(5, new java.sql.Date(endDate.getTime()));

            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error connecting to the database.");
        }

        return false;
    }
}
