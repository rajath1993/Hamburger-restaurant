package com.hamburger.administration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hamburger.administration.model.Reservation;
import com.hamburger.administration.model.User;
import com.hamburger.administration.repository.ReservationRepository;

/**
 * @author Rajath
 *
 */

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationRepository reservationRepository;

	@Override
	public Reservation createReservation(Reservation reservation) {
		
		return reservationRepository.save(reservation);
	}

	@Override
	public List<Reservation> getAllReservations() {
		List<Reservation> reservations = new ArrayList<>();
		
		reservationRepository.findAll().forEach(reservations::add);
		
		return reservations;
	}

	@Override
	public Optional<Reservation> getReservationById(String id) {
		return reservationRepository.findById(id);
	}

	@Override
	public Reservation updateReservation(String id, Reservation reservation) {
		Optional<Reservation> data = reservationRepository.findById(id);
		
		if(data!=null) {
			Reservation reservationData = data.get();
			User userData = new User();
			userData.setFirstName(reservation.getUser().getFirstName());
			userData.setLastName(reservation.getUser().getLastName());
			userData.setPhoneNumber(reservation.getUser().getPhoneNumber());
			reservationData.setUser(userData);
			reservationData.setReservationType(reservation.getReservationType());
			reservationData.setTime(reservation.getTime());
			reservationData.setNumberOfPeople(reservation.getNumberOfPeople());
			reservationData.setStatus(reservation.getStatus());
			
			return reservationRepository.save(reservationData);
		}else {
			return null;
		}
	}

	@Override
	public void deleteReservation(String id) {
		
		reservationRepository.deleteById(id);
	}

	@Override
	public void deleteAllReservations() {
		reservationRepository.deleteAll();
	}

}
