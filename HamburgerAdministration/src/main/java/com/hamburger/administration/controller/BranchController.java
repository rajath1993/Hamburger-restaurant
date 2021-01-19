package com.hamburger.administration.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hamburger.administration.model.Branch;
import com.hamburger.administration.service.BranchService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import lombok.extern.log4j.Log4j2;

/**
 * @author Rajath
 *
 */

@RestController
@RequestMapping("/api")
@Log4j2
public class BranchController {
	
	@Autowired
	BranchService branchService;
	
	@GetMapping("/branches")
	@ApiOperation(value="Finds all the branches of the restaurant",
			notes="Provide a name to get a specific branch",
			response=Branch.class)
	public ResponseEntity<List<Branch>> getAllBranches(@RequestParam(required = false) String name) {
		try {
			log.info("Entering the controller to fetch all the branches");
			List<Branch> allBranches = branchService.getAllBranches(name);
			if(allBranches.isEmpty()) {
				log.warn("No data available! please add data.");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
				
			
			return new ResponseEntity<>(allBranches,HttpStatus.OK);
		}catch(Exception e) {
			log.error("Error while getting branch information!");
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/branches/{id}")
	@ApiOperation(value="Finds a branch by branch id of the restaurant",
	notes="Provide an id to get a specific branch",
	response=Branch.class)
	@ApiResponses(value= {
			@ApiResponse(code=204,message="No Content")
	})
	public ResponseEntity<Branch> getBranchById(@ApiParam(value="ID value for the branch you need to retrieve",required= true) @PathVariable("id") String id) {
		log.info("Entering the controller to fetch branch details by branch id");
		Optional<Branch> branchData = branchService.getBranchByID(id);

		if (branchData.isPresent()) {
			return new ResponseEntity<>(branchData.get(), HttpStatus.OK);
		} else {
			log.warn("No data available!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/branches")
	@ApiOperation(value="Create a new branch",
	notes="Provide necessary values",
	response=Branch.class)
	public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) {
		try {
			log.info("Entering the controller to create a new branch");
			Branch _branch = branchService.createBranch(branch);
			return new ResponseEntity<>(_branch, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("unable to create a new branch");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/branches/{id}")
	@ApiOperation(value="Updates a branch",
	notes="Provide necessary values",
	response=Branch.class)
	public ResponseEntity<Branch> updateBranch(@PathVariable("id") String id,@RequestBody Branch branch){
		
		try {
			log.info("Entering contoller to update the branch");
			if(id==null || branch==null) {
				log.error("No id or branch value provided!");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
				
			
			
			Branch branchData = branchService.updateBranch(id, branch);
			
			if(branchData!=null) {
				log.info("branch updated!");
				return new ResponseEntity<>(branch,HttpStatus.OK);
			}else {
				log.error("branch not found!");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			log.error("Error while updating branch!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/branches/{id}")
	@ApiOperation(value="Deletes a branch by ID",
	notes="Provide branch ID",
	response=Branch.class)
	public ResponseEntity<HttpStatus> deleteBranch(@PathVariable("id") String id){
		try {
			log.info("Entering the controller to delete the branch by id");
			branchService.deleteBranch(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e){
			log.error("Unable to delete at this time!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/branches")
	@ApiOperation(value="Deletes all branches",
	notes="Deletes all branches",
	response=Branch.class)
	public ResponseEntity<HttpStatus> deleteAllBranches(){
		try {
			log.info("Entering the contoller to delete all branches");
			branchService.deleteAllBranches();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			log.error("Unable to delete at this time!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
