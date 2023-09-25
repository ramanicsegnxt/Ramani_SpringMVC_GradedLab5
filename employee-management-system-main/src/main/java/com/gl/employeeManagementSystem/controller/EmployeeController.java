package com.gl.employeeManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gl.employeeManagementSystem.entity.Employee;
import com.gl.employeeManagementSystem.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping
	public String listEmployees(Model model) {
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "employees";
	}

	@GetMapping("/new")
	public String addEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "create_employee";
	}

	@PostMapping
	public String save(@ModelAttribute("employee") Employee employee) {
		employeeService.save(employee);
		return "redirect:/employees";
	}

	@GetMapping("/edit/{id}")
	public String updateEmployee(@PathVariable long id, Model model) {
		Employee employee = employeeService.findEmployeeById(id);
		if (employee != null) {
			model.addAttribute("employee", employee);
			return "edit_employee";
		}
		return "redirect:/employees";
	}

	@PostMapping("/{id}")
	public String update(@PathVariable long id, @ModelAttribute("employee") Employee employee) {
		employeeService.updateEmployee(id, employee);
		return "redirect:/employees";
	}

	@GetMapping("/{id}")
	public String deleteEmployee(@PathVariable long id) {
		employeeService.deleteEmployee(id);
		return "redirect:/employees";
	}
}
