package com.project.company.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.company.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Optional<Employee> getEmployeeById(Long id) {
		return employeeRepository.findByEmpId(id);
	}

	public void deleteEmployee(Long id) {
		Employee oldEmployee = employeeRepository.findByEmpId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with id: " + id));

		employeeRepository.delete(oldEmployee);
	}

	@Override
	public Employee updateEmployee(Employee updatedEmployee) {
		return employeeRepository.save(updatedEmployee);
	}
}
