package com.project.company.branch_supplier;

import java.io.Serializable;
import java.util.Objects;

import com.project.company.branch.Branch;

public class BranchSupplierId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Branch branchId;

	private String supplierName;

	public BranchSupplierId() {

	}

	public BranchSupplierId(Branch branchId, String supplierName) {
		super();
		this.branchId = branchId;
		this.supplierName = supplierName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BranchSupplierId branchSupplierId = (BranchSupplierId) o;
		return branchId.equals(branchSupplierId.branchId) && supplierName.equals(branchSupplierId.supplierName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(branchId, supplierName);
	}

}
