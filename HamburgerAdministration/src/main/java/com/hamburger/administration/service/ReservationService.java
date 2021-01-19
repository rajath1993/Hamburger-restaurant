package com.hamburger.administration.service;

import java.util.List;
import java.util.Optional;

import com.hamburger.administration.model.Reservation;

/**
 * @author Rajath
 *
 */

public interface ReservationService {
	public Reservation createReservation(Reservation reservation);
	public List<Reservation> getAllReservations();
	public Optional<Reservation> getReservationById(String id);
	public Reservation updateReservation(String id,Reservation reservation);
	public void deleteReservation(String id);
	public void deleteAllReservations();
}
