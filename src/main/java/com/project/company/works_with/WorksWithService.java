package com.project.company.works_with;

import java.util.List;
import java.util.Optional;

import com.project.company.client.Client;
import com.project.company.employee.Employee;

public interface WorksWithService {

	List<WorksWith> getAllWorksWith();

	WorksWith saveWorksWith(WorksWith worksWith);

	Optional<WorksWith> getWorksWithById(Employee empId, Client clientId);

	void deleteWorksWith(Employee empId, Client clientId);

	WorksWith updateWorksWith(Employee empId, Client clientId, WorksWith worksWith);
}
