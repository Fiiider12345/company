package com.project.company.branch;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/branches")
public class BranchController {

	private BranchService branchService;

	public BranchController(BranchService branchService) {
		super();
		this.branchService = branchService;
	}

	@GetMapping
	public List<Branch> getAllBranches() {
		return branchService.getAllBranches();
	}

	@PostMapping
	public Branch createBranch(@RequestBody Branch branch) {
		return branchService.saveBranch(branch);
	}

	@GetMapping("{id}")
	public Optional<Branch> getBranchById(@PathVariable("id") Long id) {
		return branchService.getBranchById(id);
	}

	@DeleteMapping("{id}")
	public void deleteBranch(@PathVariable("id") Long id) {
		branchService.deleteBranch(id);
	}

	@PutMapping("{id}")
	public Branch updateBranch(@PathVariable("id") Long id, @RequestBody Branch branch) {
		return branchService.updateBranch(id, branch);
	}
}
