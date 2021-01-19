package com.hamburger.administration.service;

import java.util.List;
import java.util.Optional;

import com.hamburger.administration.model.Menu;

/**
 * @author Rajath
 *
 */


public interface MenuService {
	public Menu createMenu(Menu menu);
	public List<Menu> getAllMenuItems();
	public List<Menu> getMenuItemsByName(String menuSection);
	public Menu updateMenu(String id,Menu menu);
	public void deleteMenu(String id);
	public void deleteAllMenu();
}
