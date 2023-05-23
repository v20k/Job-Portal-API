package edu.project.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.service.ApplicantService;
import edu.project.jobportal.util.ResponseStructure;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

	@Autowired
	private ApplicantService applicantService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Applicant>> saveApplicant(@RequestBody Applicant applicant){
		return applicantService.saveApplicant(applicant);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Applicant>> getApplicant(@RequestParam long applicantId){
		return applicantService.findApplicant(applicantId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Applicant>> updateApplicant(@RequestParam long applicantId,@RequestBody Applicant applicant){
		return applicantService.updateApplicant(applicantId,applicant);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Applicant>> deleteApplicant(@RequestParam long applicantId){
		return applicantService.deleteApplicant(applicantId);
	}
}
