package com.project.company.works_with;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.company.client.Client;
import com.project.company.employee.Employee;
import com.project.company.exception.ResourceNotFoundException;

@Service
public class WorksWithServiceImpl implements WorksWithService {

	private WorksWithRepository worksWithRepository;

	@Autowired
	public WorksWithServiceImpl(WorksWithRepository worksWithRepository) {
		super();
		this.worksWithRepository = worksWithRepository;
	}

	public List<WorksWith> getAllWorksWith() {
		return worksWithRepository.findAll();
	}

	public WorksWith saveWorksWith(WorksWith worksWith) {
		return worksWithRepository.save(worksWith);
	}

	public Optional<WorksWith> getWorksWithById(Employee empId, Client clientId) {
		WorksWithId worksWithId = new WorksWithId(empId, clientId);

		return worksWithRepository.findById(worksWithId);
	}

	public void deleteWorksWith(Employee empId, Client clientId) {
		WorksWithId worksWithId = new WorksWithId(empId, clientId);

		WorksWith oldWorksWith = worksWithRepository.findById(worksWithId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with id: " + worksWithId));

		worksWithRepository.delete(oldWorksWith);
	}

	public WorksWith updateWorksWith(Employee empId, Client clientId, WorksWith worksWith) {
		WorksWithId worksWithId = new WorksWithId(empId, clientId);

		WorksWith oldWorksWith = worksWithRepository.findById(worksWithId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with id: " + worksWithId));

		oldWorksWith.setTotalSales(worksWith.getTotalSales());

		return worksWithRepository.save(oldWorksWith);
	}
}
