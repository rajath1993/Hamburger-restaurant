package com.hamburger.administration.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hamburger.administration.model.Branch;

/**
 * @author Rajath
 *
 */

@Repository
public interface BranchRepository extends MongoRepository<Branch, String> {
	List<Branch> findByNameContaining(String name);
}
