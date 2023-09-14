package com.project.company.branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {

	List<Branch> getAllBranches();

	Branch saveBranch(Branch branch);

	Optional<Branch> getBranchById(Long id);

	void deleteBranch(Long id);

	Branch updateBranch(Long id, Branch branch);

}
