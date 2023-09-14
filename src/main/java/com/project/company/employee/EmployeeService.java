package com.project.company.employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

	List<Employee> getAllEmployees();

	Employee saveEmployee(Employee employee);

	Optional<Employee> getEmployeeById(Long id);

	void deleteEmployee(Long id);
	
	Employee updateEmployee(Employee updatedEmployee);
}
