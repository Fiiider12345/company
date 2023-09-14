package com.project.company.works_with;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.company.client.Client;
import com.project.company.employee.Employee;

@Entity
@Table(name = "works_with")
@IdClass(WorksWithId.class)
public class WorksWith implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Employee empId;

	@Id
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client clientId;

	@Column(name = "total_sales")
	private Long totalSales;

	public WorksWith() {

	}

	public WorksWith(Employee empId, Client clientId, Long totalSales) {
		super();
		this.empId = empId;
		this.clientId = clientId;
		this.totalSales = totalSales;
	}

	public Employee getEmpId() {
		return empId;
	}

	public void setEmpId(Employee empId) {
		this.empId = empId;
	}

	public Client getClientId() {
		return clientId;
	}

	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}

	public Long getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(Long totalSales) {
		this.totalSales = totalSales;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
