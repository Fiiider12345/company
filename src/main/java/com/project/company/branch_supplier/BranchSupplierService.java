package com.project.company.branch_supplier;

import java.util.List;
import java.util.Optional;

import com.project.company.branch.Branch;

public interface BranchSupplierService {

	List<BranchSupplier> getAllBranchSuppliers();

	BranchSupplier saveBranchSupplier(BranchSupplier branchSupplier);

	Optional<BranchSupplier> getBranchSupplierById(Branch branchId, String supplierName);

	void deleteBranchSupplier(Branch branchId, String supplierName);

	BranchSupplier updateBranchSupplier(Branch branchId, String supplierName, BranchSupplier branchSupplier);
}
