package com.gl.employeeManagementSystem.service;

import java.util.List;

import com.gl.employeeManagementSystem.entity.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();

	public Employee findEmployeeById(long id);

	public Employee save(Employee employee);

	public Employee updateEmployee(long id, Employee employee);

	public void deleteEmployee(long id);
}
