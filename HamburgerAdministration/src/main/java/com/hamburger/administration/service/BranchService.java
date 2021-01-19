package com.hamburger.administration.service;

import java.util.List;
import java.util.Optional;

import com.hamburger.administration.model.Branch;

/**
 * @author Rajath
 *
 */

public interface BranchService {
	public List<Branch> getAllBranches(String name);
	public Optional<Branch> getBranchByID(String id);
	public Branch createBranch(Branch branch);
	public Branch updateBranch(String id,Branch branch);
	public void deleteBranch(String id);
	public void deleteAllBranches();
}
