package com.project.company.works_with;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.company.client.Client;
import com.project.company.employee.Employee;

@RestController
@RequestMapping(path = "/api/works_with")
public class WorksWithController {

	private WorksWithService worksWithService;

	public WorksWithController(WorksWithService worksWithService) {
		super();
		this.worksWithService = worksWithService;
	}

	@GetMapping
	public List<WorksWith> getAllWorksWith() {
		return worksWithService.getAllWorksWith();
	}

	@PostMapping
	public WorksWith createWorksWith(@RequestBody WorksWith worksWith) {
		return worksWithService.saveWorksWith(worksWith);
	}

	@GetMapping("{empId}/{clientId}")
	public Optional<WorksWith> getWorksWithById(@PathVariable("empId") Employee empId,
			@PathVariable("clientId") Client clientId) {
		return worksWithService.getWorksWithById(empId, clientId);
	}

	@DeleteMapping("{empId}/{clientId}")
	public void deleteWorksWith(@PathVariable("empId") Employee empId, @PathVariable("clientId") Client clientId) {
		worksWithService.deleteWorksWith(empId, clientId);
	}

	@PutMapping("{empId}/{clientId}")
	public WorksWith updateWorksWith(@PathVariable("empId") Employee empId, @PathVariable("clientId") Client clientId,
			@RequestBody WorksWith worksWith) {
		return worksWithService.updateWorksWith(empId, clientId, worksWith);
	}
}
