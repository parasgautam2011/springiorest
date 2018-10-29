package com.example.rest.restfulwebservices.spring.io;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	private EmployeeRepository repository;

	EmployeeController(EmployeeRepository repository){
		this.repository = repository;
	}

	@GetMapping("/employees")
	public List<Employee> all(){
		return repository.findAll();
	}

	@PostMapping("/newEmployee")
	public Employee newEmployee(@RequestBody Employee newEmployee) {
		return repository.save(newEmployee);
	}

	@GetMapping("/employees/{id}")
	public Employee oneEmployee(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
	}

	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
		return repository.findById(id)
				.map(emp -> {
					emp.setName(employee.getName());
					emp.setRole(employee.getRole());
					return repository.save(emp);
		})
				.orElseGet(()->{
					employee.setId(id);
					return repository.save(employee);
		});
	}
	
	@DeleteMapping("/employees/{id}")
	public void removeEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
