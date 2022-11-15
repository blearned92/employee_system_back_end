package com.infosys.spa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.spa.models.Employee;
import com.infosys.spa.services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeService employeeService;
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//http://localhost:5000/user/all
	//Returns JSON Array of all users
	@GetMapping(value="/all")
	public List<Employee> findAllEmployees(){
		return this.employeeService.findAllEmployees();
	}
	
	//http://localhost:5000/employee/employeename?employeename=Brandon
	/*Returns JSON value of: 
	{
	    "id": 5,
	    "employeeName": "Brandon",
	    "employeeAddress": "Colorado",
	    "employeeSalary": 1000
	} 
	*/
	@GetMapping(value = "/employeename")
	public Employee findEmployeeByEmployeeName(@RequestParam String employeename) {
		return this.employeeService.findByEmployeeName(employeename);
	}
	
	//http://localhost:5000/employee/new
	/*Must pass in JSON body without id 
	{
	    "employeeName": "Brandon",
	    "employeeAddress": "Colorado",
	    "employeeSalary": 1000
	} 
	*/
	@PostMapping(value="/new", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody Employee employee) {
		this.employeeService.save(employee);
	}
	
	//http://localhost:5000/employee/update
	/*Must pass in JSON body with id and all other info can be different
	{
		    "id":6,
			"employeeName":"Charles",
			"employeeAddress":"New York",
			"employeeSalary":100000
	} 
	*/
	@PutMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody Employee employee) {
		this.employeeService.save(employee);
	}
	
	//http://localhost:5000/employee/delete/Brandon
	@DeleteMapping(value="/delete/{employeename}")
	public String deleteEmployeeByEmployeeName(@PathVariable String employeename) {
		Employee employee = this.employeeService.findByEmployeeName(employeename);
		if(employee != null) {
			this.employeeService.delete(employee);
			return "Employee " + employee.getEmployeeName() + " Successfully Deleted";
		} else {
			return "Employee Does Not Exist";
		}
	}
}
