package com.infosys.spa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="employees")
public class Employee {

	@Id //Primary Key
	@Column(name="employee_id") 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="employee_name")
	private String employeeName;
	@Column(name="employee_address")
	private String employeeAddress;
	@Column(name="employee_salary")
	private int employeeSalary;
	
	public Employee() {
		super();
	}

	public Employee(String employeeName, String employeeAddress, int employeeSalary) {
		super();
		this.employeeName = employeeName;
		this.employeeAddress = employeeAddress;
		this.employeeSalary = employeeSalary;
	}

	public Employee(int id, String employeeName, String employeeAddress, int employeeSalary) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.employeeAddress = employeeAddress;
		this.employeeSalary = employeeSalary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public int getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(int employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeName=" + employeeName + ", employeeAddress=" + employeeAddress
				+ ", employeeSalary=" + employeeSalary + "]";
	}

	
	
}
