package com.project.company.employee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository underTest;

	@Test
	public void itShouldFindEmployeeByEmpId() {
		// give
		Long id = Long.valueOf(1);
		Employee employee = new Employee();
		underTest.save(employee);

		// when
		Optional<Employee> findedEmployee = underTest.findByEmpId(id);

		// then
		assertTrue(findedEmployee.isPresent());
		assertEquals(id, findedEmployee.get().getEmpId());
	}
}
