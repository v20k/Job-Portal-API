package edu.project.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.jobportal.entity.JobApplication;
import edu.project.jobportal.service.JobApplicationService;
import edu.project.jobportal.util.ResponseStructure;

@RestController
@RequestMapping("/jobApplication")
public class JobApplicationController {

	@Autowired
	private JobApplicationService jobApplicationService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<JobApplication>> saveJobApplication(@RequestParam long jobId,@RequestParam long applicantId){
		return jobApplicationService.saveJobApplication(jobId, applicantId);
	}
}
