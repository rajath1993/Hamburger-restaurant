package com.hamburger.administration.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hamburger.administration.model.Menu;

/**
 * @author Rajath
 *
 */

@Repository
public interface MenuRepository extends MongoRepository<Menu, String> {
	List<Menu> findByMenuSectionContaining(String menuSection);
}
