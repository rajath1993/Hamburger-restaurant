package com.hamburger.administration.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hamburger.administration.model.Branch;
import com.hamburger.administration.model.Menu;
import com.hamburger.administration.service.MenuService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

/**
 * @author Rajath
 *
 */

@RestController
@RequestMapping("/api")
@Log4j2
public class MenuController {
	
	@Autowired
	MenuService menuService;
	
	@GetMapping("/menuItems")
	@ApiOperation(value="Retrieves all menu items",
	notes="Retrieves all menu items",
	response=Menu.class)
	public ResponseEntity<List<Menu>> getAllMenuItems(){
		try {
			log.info("Entering the controller to fetch all the menu items");
			List<Menu> allMenuItems = menuService.getAllMenuItems();
			if(allMenuItems.isEmpty()) {
				log.error("menu items not found!");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
				
			
			return new ResponseEntity<>(allMenuItems,HttpStatus.OK);
		}catch(Exception e) {
			log.error("Unable to fetch menu items at this time!");
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/menuItems/menuSection")
	@ApiOperation(value="Retrieves menu items by menu section",
	notes="Provide the menu section to get a specific menu",
	response=Menu.class)
	public ResponseEntity<List<Menu>> getMenuItemByName(@RequestParam(name="menuSection") String menuSection){
		try {
			log.info("Entering contoller to fetch menu items by name!");
			if(menuSection==null) {
				log.error("Menu section name not provided!");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
				
			
			List<Menu> menuItemsByName = menuService.getMenuItemsByName(menuSection);
			if(menuItemsByName.isEmpty()) {
				log.error("No content available while fetching menu item by name!");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}			
			return new ResponseEntity<>(menuItemsByName,HttpStatus.OK);
		}catch(Exception e) {
			log.error("Unable to fetch menu information at this time!");
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PostMapping("/menuItems")
	@ApiOperation(value="Creates a menu",
	notes="Provide necessary information",
	response=Menu.class)
	public ResponseEntity<Menu> createMenu(@RequestBody Menu menu){
		try {
			log.info("Entering controller to add menu items");
			Menu _menu = menuService.createMenu(menu);
			return new ResponseEntity<>(_menu,HttpStatus.CREATED);
		}catch(Exception e) {
			log.error("Unable to create menu at this time!"+e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/menuItems/{id}")
	@ApiOperation(value="Updates a menu",
	notes="Provide necessary information",
	response=Menu.class)
	public ResponseEntity<Menu> updateMenu(@PathVariable("id") String id, @RequestBody Menu menu){
		try {
			log.info("Entering the controller to update menu items by menu item ID and menu object");
			if(id==null || menu == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
				
			
			Menu menuData = menuService.updateMenu(id, menu);
			if(menuData!=null)
				return new ResponseEntity<>(menu,HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			log.error("Unable to update at this time! "+e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/menuItems/{id}")
	@ApiOperation(value="Deletes a menu",
	notes="deletes a menu by menu id",
	response=Menu.class)
	public ResponseEntity<HttpStatus> deleteMenuItem(@PathVariable("id") String id){
		try {
			if(id==null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
			menuService.deleteMenu(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/menuItems")
	@ApiOperation(value="Deletes all menu items",
	notes="Deletes all menu items",
	response=Menu.class)
	public ResponseEntity<HttpStatus> deleteMenuItem(){
		try {
			menuService.deleteAllMenu();
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
