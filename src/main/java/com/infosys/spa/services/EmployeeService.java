package com.infosys.spa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.spa.models.Employee;
import com.infosys.spa.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	public EmployeeRepository employeeRepository;
	
	@Autowired
	private void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> findAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public Employee findByEmployeeName(String employeeName) {
		return this.employeeRepository.findByEmployeeName(employeeName);
	}
	
	public void save(Employee employee) {
		this.employeeRepository.save(employee);
	}
	
	public void delete(Employee employee) {
		this.employeeRepository.delete(employee);
	}
}
