package com.grocerymanagement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grocerymanagement.entity.Employee;
import com.grocerymanagement.repository.EmployeeRepository;
import com.grocerymanagement.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getAllEmployees() {

		return employeeRepository.findAll();

	}

	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	@Override
	public Object getEmployeeById(Long id) {
		
		return employeeRepository.findById(id).get();
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployeeById(Long id) {
		employeeRepository.deleteById(id);
		
	}


}
