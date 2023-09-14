package com.project.company.works_with;

import java.io.Serializable;
import java.util.Objects;

import com.project.company.client.Client;
import com.project.company.employee.Employee;

public class WorksWithId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Employee empId;

	private Client clientId;

	public WorksWithId() {

	}

	public WorksWithId(Employee empId, Client clientId) {
		super();
		this.empId = empId;
		this.clientId = clientId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		WorksWithId worksWithId = (WorksWithId) o;
		return empId.equals(worksWithId.empId) && clientId.equals(worksWithId.clientId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(empId, clientId);
	}

	@Override
	public String toString() {
		return "WorksWithId [empId=" + empId + ", clientId=" + clientId + "]";
	}

}
