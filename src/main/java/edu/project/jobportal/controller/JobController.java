package edu.project.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.jobportal.dto.JobDTO;
import edu.project.jobportal.entity.Job;
import edu.project.jobportal.service.JobService;
import edu.project.jobportal.util.ResponseStructure;

@RestController
@RequestMapping("/job")
public class JobController {

	@Autowired
	private JobService jobService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Job>> saveJob(@RequestBody JobDTO jobDTO,@RequestParam long employerId){
		return jobService.saveJob(jobDTO,employerId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Job>> deleteJob(@RequestParam long jobId){
		return jobService.deleteJob(jobId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Job>> updateJob(@RequestBody JobDTO jobDTO,@RequestParam long employerId,@RequestParam long jobId){
        return jobService.UpdateJob(jobDTO, employerId, jobId);	
	}
}
