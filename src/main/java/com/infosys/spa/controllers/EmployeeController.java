package com.infosys.spa.controllers;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infosys.spa.dto.AddressDTO;
import com.infosys.spa.dto.EmployeeDTO;
import com.infosys.spa.models.Employee;
import com.infosys.spa.services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService; 
	
	@GetMapping(value = "/{id}")
	public EmployeeDTO findEmployeeById(@PathVariable int id) {
		return this.employeeService.findById(id);
	}
	
	@GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<EmployeeDTO> findAllEmployees(){
		return this.employeeService.findAllEmployees();
	}
	
	@PostMapping(value="/new", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Employee save(@RequestBody EmployeeDTO employeeDTO) {
		RestTemplate template = new RestTemplate();
		Employee employee = employeeDTO.createEntity();
		HttpEntity<AddressDTO> addressDTO = new HttpEntity<>(employeeDTO.getAddress());
		int num = template.postForObject(
				"http://localhost:5100/address/", addressDTO,
				Integer.class);
		employee.setAddressId(num);
		this.employeeService.save(employee);
		return employee;
	}
	
	@PutMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody EmployeeDTO employeeDTO) {
		RestTemplate template = new RestTemplate();
		Employee employee = employeeDTO.createEntity();
		HttpEntity<AddressDTO> addressDTO = new HttpEntity<>(employeeDTO.getAddress());
		ResponseEntity<AddressDTO> dto = template.exchange("http://localhost:5100/address/update", 
				HttpMethod.PUT, addressDTO, AddressDTO.class);
		employee.setId(employeeDTO.getId());
		employee.setAddressId(dto.getBody().getId());
		this.employeeService.save(employee);
	}
//	
//	//http://localhost:5000/employee/delete/Brandon
	@DeleteMapping(value="/delete/{employeeId}")
	public String deleteEmployeeByEmployeeName(@PathVariable int employeeId) {
		RestTemplate template = new RestTemplate();
		EmployeeDTO employeeDTO = this.employeeService.findById(employeeId);
		Employee employee = employeeDTO.createEntity();
		employee.setId(employeeId);
		System.out.println(employee);
		if(employee != null) {
			template.delete("http://localhost:5100/address/delete/"
					+employeeDTO.getAddress().getId());
			this.employeeService.delete(employee);
			return "Employee " + employee.getFirstName() + " " + employee.getLastName() + " Successfully Deleted";
		} 
			return "Employee Does Not Exist";
		
	}
}
