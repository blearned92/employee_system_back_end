package com.infosys.spa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.infosys.spa.dto.AddressDTO;
import com.infosys.spa.dto.EmployeeDTO;
import com.infosys.spa.models.Employee;
import com.infosys.spa.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	public EmployeeRepository employeeRepository;
	
	@Autowired
	private void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public EmployeeDTO findById(int id) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		Optional<Employee> employee = this.employeeRepository.findById(id);
		if(employee.isPresent()) {
			employeeDTO = EmployeeDTO.valueOf(employee.get());
		}
		return employeeDTO;
	}
	
	public List<EmployeeDTO> findAllEmployees(){
		List<Employee> allEmployees = employeeRepository.findByOrderByIdAsc();
		List<EmployeeDTO> allEmployeeDTOs = new ArrayList<>();
		RestTemplate template = new RestTemplate();
			
		for(Employee employee : allEmployees) {
			EmployeeDTO employeeDTO = EmployeeDTO.valueOf(employee);
			AddressDTO addressDTO = template.getForObject(
					"http://localhost:5100/address/" + employee.getAddressId(), 
					AddressDTO.class);
			employeeDTO.setId(employee.getId());	
			employeeDTO.setFirstName(employee.getFirstName());
			employeeDTO.setLastName(employee.getLastName());
			employeeDTO.setSalary(employee.getSalary());
			employeeDTO.setAddress(addressDTO);
			allEmployeeDTOs.add(employeeDTO);
			
		}
		return allEmployeeDTOs;
	}
	
	public Employee save(Employee employee) {
		return this.employeeRepository.save(employee);
	}
	
	public void delete(Employee employee) {
		this.employeeRepository.delete(employee);
	}
}
