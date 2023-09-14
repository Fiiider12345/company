package com.project.company.branch_supplier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.company.branch.Branch;
import com.project.company.exception.ResourceNotFoundException;

@Service
public class BranchSupplierServiceImpl implements BranchSupplierService {

	private BranchSupplierRepository branchSupplierRepository;

	@Autowired
	public BranchSupplierServiceImpl(BranchSupplierRepository branchSupplierRepository) {
		super();
		this.branchSupplierRepository = branchSupplierRepository;
	}

	public List<BranchSupplier> getAllBranchSuppliers() {
		return branchSupplierRepository.findAll();
	}

	public BranchSupplier saveBranchSupplier(BranchSupplier branchSupplier) {
		return branchSupplierRepository.save(branchSupplier);
	}

	public Optional<BranchSupplier> getBranchSupplierById(Branch branchId, String supplierName) {
		BranchSupplierId branchSupplierId = new BranchSupplierId(branchId, supplierName);

		return branchSupplierRepository.findById(branchSupplierId);
	}

	public void deleteBranchSupplier(Branch branchId, String supplierName) {
		BranchSupplierId branchSupplierId = new BranchSupplierId(branchId, supplierName);

		BranchSupplier oldBranchSupplier = branchSupplierRepository.findById(branchSupplierId).orElseThrow(
				() -> new ResourceNotFoundException("Employee does not exists with id: " + branchSupplierId));

		branchSupplierRepository.delete(oldBranchSupplier);
	}

	public BranchSupplier updateBranchSupplier(Branch branchId, String supplierName, BranchSupplier branchSupplier) {
		BranchSupplierId branchSupplierId = new BranchSupplierId(branchId, supplierName);

		BranchSupplier oldBranchSupplier = branchSupplierRepository.findById(branchSupplierId).orElseThrow(
				() -> new ResourceNotFoundException("Employee does not exists with id: " + branchSupplierId));

		oldBranchSupplier.setSupplyType(branchSupplier.getSupplyType());

		return branchSupplierRepository.save(oldBranchSupplier);
	}
}
