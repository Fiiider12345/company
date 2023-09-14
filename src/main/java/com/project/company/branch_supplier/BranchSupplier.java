package com.project.company.branch_supplier;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.company.branch.Branch;

@Entity
@Table(name = "branch_suppliers")
@IdClass(BranchSupplierId.class)
public class BranchSupplier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branchId;

	@Id
	@Column(name = "supplier_name")
	private String supplierName;

	@Column(name = "supply_type")
	private String supplyType;

	public BranchSupplier() {

	}

	public BranchSupplier(Branch branchId, String supplierName, String supplyType) {
		super();
		this.branchId = branchId;
		this.supplierName = supplierName;
		this.supplyType = supplyType;
	}

	public Branch getBranchId() {
		return branchId;
	}

	public void setBranchId(Branch branchId) {
		this.branchId = branchId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BranchSupplier [branchId=" + branchId + ", supplierName=" + supplierName + "]";
	}

}
