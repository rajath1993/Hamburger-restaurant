package com.hamburger.administration.model;

import java.util.UUID;


import lombok.Data;

@Data
public class User {
	
	private UUID userId  = UUID.randomUUID();
	private String firstName;
	private String lastName;
	private String phoneNumber;
}
