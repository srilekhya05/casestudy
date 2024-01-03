package com.carconnect.dao;


import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.carconnect.entity.*;
import com.carconnect.exception.*;


public class ReservationServicedaoTest {
    private IReservationServicedao reservationDAO;

    @Before
    public void setUp() throws Exception {
        reservationDAO = new ReservationServicedao();
    }

    @After
    public void tearDown() throws Exception {
        reservationDAO = null;
    }

    @Test
    public void testGetReservationById() throws ReservationException, SQLException {
        Reservation reservation = null;
        int reservationId = 1;
        try {
            reservation = reservationDAO.getReservationById(reservationId);
        } catch (ClassNotFoundException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        assertTrue(reservation != null);
    }

    @Test
    public void testCreateReservation() throws SQLException {

        Reservation reservation = new Reservation();
        reservation.setCustomerID(1);
        reservation.setVehicleID(1);
        reservation.setStartDate(parseDate("2023-01-01 10:00:00"));
        reservation.setEndDate(parseDate("2023-01-05 14:00:00"));
        reservation.setTotalCost(1000.0);
        reservation.setStatus("Pending");
        int result=0;

        try {
            reservationDAO.createReservation(reservation);
            result=1;
        } catch (ClassNotFoundException | DatabaseConnectionException | ReservationException e) {
            e.printStackTrace();
        }
        assertTrue(result==1);
    }

    @Test
    public void testUpdateReservation() throws SQLException {
     

        Reservation reservation = new Reservation();
        reservation.setReservationID(1);
        reservation.setStartDate(parseDate("2023-01-10 12:00:00"));
        reservation.setEndDate(parseDate("2023-01-15 16:00:00"));
        reservation.setTotalCost(1500.0);
        reservation.setStatus("Confirmed");
        int result=0;

        try {
            reservationDAO.updateReservation(reservation);
            result=1;
        } catch (ClassNotFoundException | DatabaseConnectionException | ReservationException e) {
            e.printStackTrace();
        }
        assertTrue(result==1);
    }

    @Test
    public void testCancelReservation() {
        int reservationId = 1;
        int result=0;
        try {
            reservationDAO.cancelReservation(reservationId);
            result=1;
        } catch (ClassNotFoundException | DatabaseConnectionException | ReservationException e) {
            e.printStackTrace();
        }
        assertTrue(result==1);
    }

    
    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
