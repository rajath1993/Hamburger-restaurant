package com.hamburger.administration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hamburger.administration.model.Branch;
import com.hamburger.administration.repository.BranchRepository;


/**
 * @author Rajath
 *
 */


@Service
public class BranchServiceImpl implements BranchService{
	
	@Autowired
	BranchRepository branchRepository;
	

	@Override
	public List<Branch> getAllBranches(String name) {
		List<Branch> branch = new ArrayList<>();
		if(name == null) {
			branchRepository.findAll().forEach(branch::add);
		}else {
			branchRepository.findByNameContaining(name).forEach(branch::add);
		}
		
		return branch;
	}

	@Override
	public Optional<Branch> getBranchByID(String id) {
		return branchRepository.findById(id);
	}

	@Override
	public Branch createBranch(Branch branch) {
		
		return branchRepository.save(branch);
	}

	@Override
	public Branch updateBranch(String id, Branch branch) {
		Optional<Branch> data = branchRepository.findById(id);
		
		if(data!=null) {
			Branch branchData = data.get();
			branchData.setName(branch.getName());
			branchData.setLatitude(branch.getLatitude());
			branchData.setLongitude(branch.getLongitude());
			branchData.setPhoneNumber(branch.getPhoneNumber());
			branchData.setStreetAddress(branch.getStreetAddress());
			
			return branchRepository.save(branchData);
		}else {
			return null;
		}

	}

	@Override
	public void deleteBranch(String id) {
		branchRepository.deleteById(id);
	}

	@Override
	public void deleteAllBranches() {
		branchRepository.deleteAll();
	}

}
