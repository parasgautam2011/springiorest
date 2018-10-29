package com.example.rest.restfulwebservices.spring.io;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Employee {
	
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String role;
	
	Employee(){
		
	}
	
	Employee(String name, String role){
		this.name=name;
		this.role=role;
	}
}
