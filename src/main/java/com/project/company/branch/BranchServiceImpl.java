package com.project.company.branch;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.company.exception.ResourceNotFoundException;

@Service
public class BranchServiceImpl implements BranchService {

	private BranchRepository branchRepository;

	@Autowired
	public BranchServiceImpl(BranchRepository branchRepository) {
		super();
		this.branchRepository = branchRepository;
	}

	public List<Branch> getAllBranches() {
		return branchRepository.findAll();
	}

	public Branch saveBranch(Branch branch) {
		return branchRepository.save(branch);
	}

	public Optional<Branch> getBranchById(Long id) {
		return branchRepository.findByBranchId(id);
	}

	public void deleteBranch(Long id) {
		Branch oldBranch = branchRepository.findByBranchId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Branch does not exists with id: " + id));

		branchRepository.delete(oldBranch);
	}

	public Branch updateBranch(Long id, Branch branch) {
		Branch oldBranch = branchRepository.findByBranchId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Branch does not exists with id: " + id));

		oldBranch.setBranchName(branch.getBranchName());
		oldBranch.setMgrStartDate(branch.getMgrStartDate());

		return branchRepository.save(oldBranch);
	}
}
