package com.hamburger.administration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author Rajath
 *
 */
@Document(collection = "reservations")
@Data
public class Reservation {
	@Id
	private String id;
	private User user;
	private String reservationType;
	private String time;
	private String numberOfPeople;
	private String status;
}
