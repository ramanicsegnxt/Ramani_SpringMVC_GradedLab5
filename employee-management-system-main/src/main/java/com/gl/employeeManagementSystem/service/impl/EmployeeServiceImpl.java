package com.gl.employeeManagementSystem.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.employeeManagementSystem.entity.Employee;
import com.gl.employeeManagementSystem.repository.EmployeeRepository;
import com.gl.employeeManagementSystem.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	/**
	 * Retrieves list of employees
	 */
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	/**
	 * Retrieves employee details for the Employee ID provided
	 * 
	 * Returns null if employee details not available for provided employee id
	 */
	@Override
	public Employee findEmployeeById(long employeeId) {
		Employee employee = null;
		try {
			employee = employeeRepository.findById(employeeId).get();
		} catch (NoSuchElementException ex) {
			log.info("No Employee Records Available for id " + employeeId);
		}
		return employee;
	}

	/**
	 * Saves provided employee details to database
	 */
	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	/**
	 * Updates Employee details
	 * 
	 * Returns null if employee details is not available for provided id
	 */
	@Override
	public Employee updateEmployee(long employeeId, Employee employee) {
		Employee existing = findEmployeeById(employeeId);
		if (existing != null) {
			existing.setFirstName(employee.getFirstName());
			existing.setLastName(employee.getLastName());
			existing.setEmail(employee.getEmail());
			return employeeRepository.save(existing);
		}
		return null;
	}

	/**
	 * Delete's Employee details for provide employee id
	 */
	@Override
	public void deleteEmployee(long employeeId) {
		try {
			employeeRepository.deleteById(employeeId);
		} catch (IllegalArgumentException ex) {
			log.info("No Employee Records Available for id " + employeeId);
		}
	}

}