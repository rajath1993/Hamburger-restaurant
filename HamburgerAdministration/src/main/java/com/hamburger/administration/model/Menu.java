package com.hamburger.administration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author Rajath
 *
 */

@Document(collection = "menuItems")
@Data
public class Menu {
	
	@Id
	private String id;
	private String menuSection;
	private String menuItem;
	private double price;

}
