package com.grocerymanagement.service;

import java.util.List;

import com.grocerymanagement.entity.Employee;



public interface EmployeeService {
	List<Employee> getAllEmployees();
	
	Employee saveEmployee(Employee employee);
	
	Object getEmployeeById(Long id);
	
	Employee updateEmployee(Employee employee);
	
	void deleteEmployeeById(Long id);
	
	int avgManagerWage();
	
	int avgAssociateWage();
	
	int produceEmployeeCount();
	
	int groceryEmployeeCount();
	
	int dairyEmployeeCount();
	
	
	


	
}
