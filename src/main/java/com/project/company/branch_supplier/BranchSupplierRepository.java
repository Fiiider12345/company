package com.project.company.branch_supplier;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchSupplierRepository extends JpaRepository<BranchSupplier, BranchSupplierId> {

	Optional<BranchSupplier> findById(BranchSupplierId branchSupplierId);
}
