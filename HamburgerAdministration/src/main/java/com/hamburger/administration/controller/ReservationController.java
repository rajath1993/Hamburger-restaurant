package com.hamburger.administration.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hamburger.administration.model.Menu;
import com.hamburger.administration.model.Reservation;
import com.hamburger.administration.service.ReservationService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

/**
 * @author Rajath
 *
 */
@RestController
@RequestMapping("/api")
@Log4j2
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/reservations")
	@ApiOperation(value="Retrieves all reservations",
	notes="Retrieves all menu items",
	response=Menu.class)
	public ResponseEntity<List<Reservation>> getAllReservations(){
		try {
			log.info("Entering the controller to retrieve all reservations");
			List<Reservation> reservations = reservationService.getAllReservations();
			if(reservations.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			return new ResponseEntity<>(reservations,HttpStatus.OK);
		}catch(Exception e) {
			log.error("Unable to retrieve at this time! "+e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/reservations/{id}")
	@ApiOperation(value="Retrieves reservations by id",
	notes="Retrieves retrieves reservations by id",
	response=Menu.class)
	public ResponseEntity<Optional<Reservation>> getReservationById(@PathVariable("id") String id){
		try {
			log.info("Entering the controller to fetch reservation by id");
			if(id == null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			Optional<Reservation> reservation = reservationService.getReservationById(id);
			if(reservation.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			
			return new ResponseEntity<>(reservation,HttpStatus.OK);
		}catch(Exception e) {
			log.error("Unable to fetch at this time! "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/reservations")
	@ApiOperation(value="create a reservation",
	notes="provide necessary details",
	response=Menu.class)
	public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
		try {
			log.info("Entering the controller to create a new reservation");
			Reservation _reservation = reservationService.createReservation(reservation);
			return new ResponseEntity<>(_reservation,HttpStatus.CREATED);
		}catch(Exception e) {
			log.error("Unable to create a reservation at this time! "+e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/reservations/{id}")
	@ApiOperation(value="Update reservation",
	notes="Update reservations",
	response=Menu.class)
	public ResponseEntity<Reservation> updateReservation(@PathVariable("id") String id,@RequestBody Reservation reservation){
		try {
			log.info("Entering controller to update reservation information!");
			if(id==null || reservation == null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
			Reservation reservationData = reservationService.updateReservation(id, reservation);
			if(reservationData!=null)
				return new ResponseEntity<>(reservation,HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			log.error("Unable to update at this time! "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/reservations/{id}")
	@ApiOperation(value="Delete Reservation by id",
	notes="Delete Reservation by id",
	response=Menu.class)
	public ResponseEntity<HttpStatus> deleteReservation(@PathVariable("id") String id){
		try {
			log.info("Entering controller to delete reservation!");
			if(id==null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
			reservationService.deleteReservation(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			log.error("Unable to delete at this time! "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/reservations")
	@ApiOperation(value="Delete all Reservations",
	notes="Delete all Reservations",
	response=Menu.class)
	public ResponseEntity<HttpStatus> deleteAllReservations(){
		try {
			log.info("Entering controller to delete all reservations!");
			reservationService.deleteAllReservations();
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			log.error("Unable to delete at this time! "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
