package edu.project.jobportal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.jobportal.entity.Employer;
import edu.project.jobportal.service.EmployerService;
import edu.project.jobportal.util.ResponseStructure;


@RestController
@RequestMapping("/employer")
public class EmployerController {

	@Autowired
	private EmployerService employerService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Employer>> saveEmployer( @RequestBody @Valid Employer employer){
		return employerService.saveEmployer(employer);                             //this valid goes to exception class where it hits ResponseEntityExceptionHandler and it override handleMethodArgumentNotValid method
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Employer>> findEmployer(@RequestParam long employerId){
		return employerService.findEmployer(employerId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Employer>> deleteEmployer(@RequestParam long employerId){
		return employerService.deleteEmployer(employerId);
	}
}
