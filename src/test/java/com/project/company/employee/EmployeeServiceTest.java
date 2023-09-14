package com.project.company.employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.company.branch.Branch;
import com.project.company.exception.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	private Employee employee;

	@BeforeEach
	public void setUp() {
		Set<Employee> superEmployee = new HashSet<Employee>();
		Branch branch = new Branch();
		employee = new Employee("Marek", "Pecho", Date.valueOf("1995-06-19"), 'M', Long.valueOf(15000), superEmployee,
				branch);
		employee.setEmpId(1L);
	}

	// JUnit test for getAllEmployees method
	@DisplayName("JUnit test for getAllEmployees method")
	@Test
	public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList() {
		// given - precondition or setup
		given(employeeRepository.findAll()).willReturn(List.of(employee));

		// when - action or the behaviour that we are going test
		List<Employee> employeeList = employeeService.getAllEmployees();

		// then - verify the output
		assertThat(employeeList).isNotNull();
		assertThat(employeeList.size()).isEqualTo(1);
	}

	// JUnit test for saveEmployee method
	@DisplayName("JUnit test for saveEmployee method")
	@Test
	public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
		// given
		given(employeeRepository.save(employee)).willReturn(employee);

		// when
		Employee savedEmployee = employeeService.saveEmployee(employee);

		// then
		assertThat(savedEmployee).isNotNull();
	}

	// JUnit test for getEmployeeById method
	@DisplayName("JUnit test for getEmployeeById method")
	@Test
	public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() {
		// given
		long employeeId = 1L;

		given(employeeRepository.findByEmpId(employeeId)).willReturn(Optional.of(employee));

		// when
		Employee savedEmployee = employeeService.getEmployeeById(employee.getEmpId()).get();

		// then
		assertThat(savedEmployee).isNotNull();
	}

	// JUnit test for deleteEmployee method
	@DisplayName("JUnit test for deleteEmployee method")
	@Test
	public void givenEmployeeId_whenDeleteEmployee_thenNothing() {
		// given
		long employeeId = 1L;

		given(employeeRepository.findByEmpId(employeeId)).willReturn(Optional.of(employee));

		// when
		employeeService.deleteEmployee(employeeId);

		// then
		verify(employeeRepository, times(1)).delete(employee);
	}


	
	 // JUnit test for updateEmployee method
    @DisplayName("JUnit test for updateEmployee method")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        // given - precondition or setup
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setFirstName("Juraj");
		employee.setBirthDate(Date.valueOf("2004-03-15"));
		employee.setLastName("Pecho");
		employee.setSalary(20000L);
		employee.setSex('M');
		
        // when -  action or the behaviour that we are going test
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        // then - verify the output
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Juraj");
		assertThat(updatedEmployee.getBirthDate()).isEqualTo(Date.valueOf("2004-03-15"));
		assertThat(updatedEmployee.getLastName()).isEqualTo("Pecho");
		assertThat(updatedEmployee.getSalary()).isEqualTo(20000L);
		assertThat(updatedEmployee.getSex()).isEqualTo('M');
    }

	// JUnit test for deleteEmployee method
	@DisplayName("JUnit test for deleteEmployee method which throws exception")
	@Test
	public void givenNotExistingId_whenDeleteEmployee_thenThrowsException() {
		// given
		long employeeId = 1L;

		given(employeeRepository.findByEmpId(employeeId)).willReturn(Optional.empty());

		// when
		org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			employeeService.deleteEmployee(employeeId);
		});

		// then
		verify(employeeRepository, never()).delete(any(Employee.class));
	}


}
