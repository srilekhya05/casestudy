// ReservationService.java
package com.carconnect.Service;

import com.carconnect.dao.*;
import com.carconnect.entity.*;
import com.carconnect.exception.*;


import java.util.List;

public class ReservationServiceImpl implements IReservationService {
    private IReservationServicedao reservationDao;

    public ReservationServiceImpl(IReservationServicedao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Override
    public Reservation getReservationById(int reservationId) throws DatabaseConnectionException, ClassNotFoundException {
        return reservationDao.getReservationById(reservationId);
    }

    @Override
    public List<Reservation> getReservationsByCustomerId(int customerId) throws DatabaseConnectionException, ClassNotFoundException {
        return reservationDao.getReservationsByCustomerId(customerId);
    }

    @Override
    public void createReservation(Reservation reservationData) throws DatabaseConnectionException, ReservationException, ClassNotFoundException {

        reservationDao.createReservation(reservationData);
    }

    @Override
    public void updateReservation(Reservation reservationData) throws DatabaseConnectionException, ReservationException, ClassNotFoundException {

        reservationDao.updateReservation(reservationData);
    }

    @Override
    public void cancelReservation(int reservationId) throws DatabaseConnectionException, ReservationException, ClassNotFoundException,ClassNotFoundException {
        Reservation existingReservation = reservationDao.getReservationById(reservationId);
        if (existingReservation == null) {
            throw new ReservationException("Reservation not found");
        }

       
        if (!"Pending".equals(existingReservation.getStatus())) {
            throw new ReservationException("Reservation is not cancellable");
        }

        reservationDao.cancelReservation(reservationId);
    }
}
