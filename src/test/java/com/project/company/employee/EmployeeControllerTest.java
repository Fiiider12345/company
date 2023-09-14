package com.project.company.employee;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.ArgumentMatchers.any;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.company.branch.Branch;
import com.project.company.branch.BranchService;
import com.project.company.branch_supplier.BranchSupplierService;
import com.project.company.client.ClientService;
import com.project.company.works_with.WorksWithService;

@WebMvcTest
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@MockBean
	private BranchService branchService;

	@MockBean
	private BranchSupplierService branchSupplierService;

	@MockBean
	private ClientService clientService;

	@MockBean
	private WorksWithService worksWithService;

	@Autowired
	private ObjectMapper objectMapper;

	// JUnit test for Get All employees REST API
	@Test
	public void givenListOfEmployees_whenGetAllEmployees_thenReturnEmployeesList() throws Exception {
		// given - precondition or setup
		Set<Employee> superEmployee = new HashSet<Employee>();
		Branch branch = new Branch();
		Employee employee1 = new Employee("Marek", "Pecho", Date.valueOf("1995-06-19"), 'M', Long.valueOf(15000),
				superEmployee, branch);
		Employee employee2 = new Employee("Juraj", "Pecho", Date.valueOf("1995-06-19"), 'M', Long.valueOf(15000),
				superEmployee, branch);

		List<Employee> listOfEmployees = new ArrayList<>();
		listOfEmployees.add(employee1);
		listOfEmployees.add(employee2);
		given(employeeService.getAllEmployees()).willReturn(listOfEmployees);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/api/employees"));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(listOfEmployees.size())));

	}
	
	 // Junit test for save employee REST API
	 @Test
	    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception{

	        // given - precondition or setup
		 Set<Employee> superEmployee = new HashSet<Employee>();
			Branch branch = new Branch();
			Employee employee = new Employee("Marek", "Pecho", Date.valueOf("1995-06-19"), 'M', Long.valueOf(15000),
					superEmployee, branch);
			
	        given(employeeService.saveEmployee(any(Employee.class)))
	                .willAnswer((invocation)-> invocation.getArgument(0));

	        // when - action or behaviour that we are going test
	        ResultActions response = mockMvc.perform(post("/api/employees")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(employee)));

	        // then - verify the result or output using assert statements
	        response.andDo(print()).
	                andExpect(status().isCreated())
	                .andExpect(jsonPath("$.firstName",
	                        is(employee.getFirstName())))
	                .andExpect(jsonPath("$.lastName",
	                        is(employee.getLastName())))
	                .andExpect(jsonPath("$.birthDate",
	                        is(employee.getBirthDate().toString())))
	                .andExpect(jsonPath("$.sex",
	                		is(String.valueOf(employee.getSex()))));

	    }
	 
	 // positive scenario - valid employee id
	    // JUnit test for GET employee by id REST API
	    @Test
	    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception{
	        // given - precondition or setup
	        long employeeId = 1L;
	        
	        Set<Employee> superEmployee = new HashSet<Employee>();
			Branch branch = new Branch();
			Employee employee = new Employee("Marek", "Pecho", Date.valueOf("1995-06-19"), 'M', Long.valueOf(15000),
					superEmployee, branch);
	        
	        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));

	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/api/employees/{id}", employeeId));

	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print())
	                .andExpect(jsonPath("$.firstName",
	                        is(employee.getFirstName())))
	                .andExpect(jsonPath("$.lastName",
	                        is(employee.getLastName())))
	                .andExpect(jsonPath("$.birthDate",
	                        is(employee.getBirthDate().toString())))
	                .andExpect(jsonPath("$.sex",
	                		is(String.valueOf(employee.getSex()))));
	    }
	    
	 // negative scenario - valid employee id
	    // JUnit test for GET employee by id REST API
	    @Test
	    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception{
	        // given - precondition or setup
	        long employeeId = 1L;
	        
	        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());

	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/api/employees/{id}", employeeId));

	        // then - verify the output
	        response.andExpect(status().isNotFound())
	                .andDo(print());

	    }
	    

        
     // JUnit test for update employee REST API - positive scenario
        @Test
        public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdateEmployeeObject() throws Exception{
            // given - precondition or setup
            long employeeId = 1L;
            
            Set<Employee> superEmployee = new HashSet<Employee>();
    		Branch branch = new Branch();
    		Employee savedEmployee = new Employee("Marek", "Pecho", Date.valueOf("1995-06-19"), 'M', Long.valueOf(15000),
    				superEmployee, branch);
    		Employee updatedEmployee = new Employee("Juraj", "Pecho", Date.valueOf("1995-06-19"), 'M', Long.valueOf(15000),
    				superEmployee, branch);
            
            given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(savedEmployee));
            given(employeeService.updateEmployee(any(Employee.class)))
                    .willAnswer((invocation)-> invocation.getArgument(0));

            // when -  action or the behaviour that we are going test
            ResultActions response = mockMvc.perform(put("/api/employees/{id}", employeeId)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(updatedEmployee)));


            // then - verify the output
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.firstName",
	                        is(updatedEmployee.getFirstName())))
	                .andExpect(jsonPath("$.lastName",
	                        is(updatedEmployee.getLastName())))
	                .andExpect(jsonPath("$.birthDate",
	                        is(updatedEmployee.getBirthDate().toString())))
	                .andExpect(jsonPath("$.sex",
	                		is(String.valueOf(updatedEmployee.getSex()))));
        }
        
     // JUnit test for update employee REST API - negative scenario
        @Test
        public void givenUpdatedEmployee_whenUpdateEmployee_thenReturn404() throws Exception{
            // given - precondition or setup
            long employeeId = 1L;
            
            Set<Employee> superEmployee = new HashSet<Employee>();
    		Branch branch = new Branch();
    		Employee updatedEmployee = new Employee("Juraj", "Pecho", Date.valueOf("1995-06-19"), 'M', Long.valueOf(15000),
    				superEmployee, branch);
            
            given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
            given(employeeService.updateEmployee(any(Employee.class)))
                    .willAnswer((invocation)-> invocation.getArgument(0));

            // when -  action or the behaviour that we are going test
            ResultActions response = mockMvc.perform(put("/api/employees/{id}", employeeId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updatedEmployee)));

            // then - verify the output
            response.andExpect(status().isNotFound())
                    .andDo(print());
        }
        
     // JUnit test for delete employee REST API
        @Test
        public void givenEmployeeId_whenDeleteEmployee_thenReturn200() throws Exception{
            // given - precondition or setup
            long employeeId = 1L;
            willDoNothing().given(employeeService).deleteEmployee(employeeId);

            // when -  action or the behaviour that we are going test
            ResultActions response = mockMvc.perform(delete("/api/employees/{id}", employeeId));

            // then - verify the output
            response.andExpect(status().isOk())
                    .andDo(print());
        }
}
