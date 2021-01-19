package com.hamburger.administration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.hamburger.administration.model.Menu;
import com.hamburger.administration.repository.MenuRepository;
/**
 * @author Rajath
 *
 */

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	MenuRepository menuRepository;
	

	@Override
	public Menu createMenu(Menu menu) {
		return menuRepository.save(menu);
	}

	@Override
	public List<Menu> getAllMenuItems() {
		List<Menu> menu = new ArrayList<>();
		
		menuRepository.findAll().forEach(menu::add);
		
		return menu;
	}

	@Override
	public List<Menu> getMenuItemsByName(String menuSection) {
		return menuRepository.findByMenuSectionContaining(menuSection);
	}

	@Override
	public Menu updateMenu(String id, Menu menu) {
		Optional<Menu> data = menuRepository.findById(id);
		
		if(data!=null) {
			Menu menuData = data.get();
			menuData.setMenuItem(menu.getMenuItem());
			menuData.setMenuSection(menu.getMenuSection());
			menuData.setPrice(menu.getPrice());
			
			return menuRepository.save(menuData);
		}else {
			return null;
		}
	}

	@Override
	public void deleteMenu(String id) {
		menuRepository.deleteById(id);
	}

	@Override
	public void deleteAllMenu() {
		menuRepository.deleteAll();
	}

}
