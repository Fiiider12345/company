package com.project.company.branch_supplier;

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

import com.project.company.branch.Branch;

@RestController
@RequestMapping(path = "/api/branch_supplier")
public class BranchSupplierController {

	private BranchSupplierService branchSupplierService;

	public BranchSupplierController(BranchSupplierService branchSupplierService) {
		super();
		this.branchSupplierService = branchSupplierService;
	}

	@GetMapping
	public List<BranchSupplier> getAllBranchSuppliers() {
		return branchSupplierService.getAllBranchSuppliers();
	}

	@PostMapping
	public BranchSupplier createBranchSupplier(@RequestBody BranchSupplier branchSupplier) {
		return branchSupplierService.saveBranchSupplier(branchSupplier);
	}

	@GetMapping("{branchId}/{supplierName}")
	public Optional<BranchSupplier> getBranchSupplierById(@PathVariable("branchId") Branch branchId,
			@PathVariable("supplierName") String supplierName) {
		return branchSupplierService.getBranchSupplierById(branchId, supplierName);
	}

	@DeleteMapping("{branchId}/{supplierName}")
	public void deleteBranchSupplier(@PathVariable("branchId") Branch branchId,
			@PathVariable("supplierName") String supplierName) {
		branchSupplierService.deleteBranchSupplier(branchId, supplierName);
	}

	@PutMapping("{branchId}/{supplierName}")
	public BranchSupplier updateBranchSupplier(@PathVariable("branchId") Branch branchId,
			@PathVariable("supplierName") String supplierName, @RequestBody BranchSupplier branchSupplier) {
		return branchSupplierService.updateBranchSupplier(branchId, supplierName, branchSupplier);
	}
}
