package com.example.demo.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@Autowired StudentImplementation implementation;
	
	@GetMapping("/all")
	  public List<Map<String, Object>> getAllTutorials() {
		return implementation.getAll();
	   
	     
	}
	 
	@GetMapping("/byname/{name}")
	public List<StudentEntity> getByName(@PathVariable String name){
		return implementation.findByName(name);
		
	}
	
	
	
	@PostMapping("/insert")
	 public ResponseEntity<String> createTutorial(@RequestBody StudentEntity tutorial) {
	    try {
	      implementation.saveStudent(tutorial);
	      return new ResponseEntity<>("Tutorial was created successfully.", HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	@GetMapping("/getThan80")
	
	public List<StudentEntity> getMore80(){
		return implementation.getGrater80();
	}
}

