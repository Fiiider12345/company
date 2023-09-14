package com.project.company.branch;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.project.company.employee.Employee;

@Entity
@Table(name = "branches")
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branch_id")
	private Long branchId;

	@Column(name = "branch_name")
	private String branchName;

	@OneToOne
	@JoinColumn(name = "mgr_id")
	private Employee mgrId;

	@Column(name = "mgr_start_date")
	private Date mgrStartDate;

	public Branch() {

	}

	public Branch(String branchName, Employee mgrId, Date mgrStartDate) {
		super();
		this.branchName = branchName;
		this.mgrId = mgrId;
		this.mgrStartDate = mgrStartDate;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Employee getMgrId() {
		return mgrId;
	}

	public void setMgrId(Employee mgrId) {
		this.mgrId = mgrId;
	}

	public Date getMgrStartDate() {
		return mgrStartDate;
	}

	public void setMgrStartDate(Date mgrStartDate) {
		this.mgrStartDate = mgrStartDate;
	}

}
