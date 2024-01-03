package com.carconnect.dao;

import com.carconnect.entity.*;
import com.carconnect.exception.*;


import java.util.List;

public interface IReservationServicedao {
    Reservation getReservationById(int reservationId) throws DatabaseConnectionException, ClassNotFoundException;
    List<Reservation> getReservationsByCustomerId(int customerId) throws DatabaseConnectionException, ClassNotFoundException;
    void createReservation(Reservation reservationData) throws DatabaseConnectionException, ReservationException, ClassNotFoundException;
    void updateReservation(Reservation reservationData) throws DatabaseConnectionException, ReservationException, ClassNotFoundException;
    void cancelReservation(int reservationId) throws DatabaseConnectionException, ReservationException, ClassNotFoundException;
}
