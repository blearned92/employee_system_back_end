package com.infosys.spa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.stereotype.Repository;

import com.infosys.spa.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
//	Employee findByEmployeeName(String employeeName);
	List<Employee> findByOrderByIdAsc();
}
