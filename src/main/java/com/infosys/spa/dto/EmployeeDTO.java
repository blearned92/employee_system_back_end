package com.infosys.spa.dto;

import org.springframework.web.client.RestTemplate;

import com.infosys.spa.models.Employee;

public class EmployeeDTO {

	Integer id;
	String firstName; 
	String lastName;
	AddressDTO address;
	Integer salary;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

		@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", salary=" + salary + "]";
	}

	//converts entity to dto
	public static EmployeeDTO valueOf(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		RestTemplate template = new RestTemplate();
		AddressDTO addressDTO = template.getForObject(
				"http://localhost:5100/address/"+employee.getAddressId(), 
				AddressDTO.class);
		
		employeeDTO.setId(employee.getId());
		employeeDTO.setFirstName(employee.getFirstName());
		employeeDTO.setLastName(employee.getLastName());
		employeeDTO.setSalary(employee.getSalary());
		employeeDTO.setAddress(addressDTO);
		System.out.println("Occured");
		return employeeDTO;
	}
	
	//converts dto to entity
	public Employee createEntity() {
		Employee employee = new Employee();
		employee.setFirstName(this.getFirstName());
		employee.setLastName(this.getLastName());
		employee.setAddressId(this.getAddress().getId());
		employee.setSalary(this.getSalary());
		
		return employee;
	}
	
	
}
