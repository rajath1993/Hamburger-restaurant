/**
 * 
 */
package com.hamburger.administration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author Rajath
 *
 */

@Document(collection = "branches")
@Data
public class Branch {
	@Id
	private String id;
	private String name;
	private String streetAddress;
	private String latitude;
	private String longitude;
	private String phoneNumber;
}
