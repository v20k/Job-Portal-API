package edu.project.jobportal.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.EmployerDAO;
import edu.project.jobportal.dao.JobDAO;
import edu.project.jobportal.dto.JobDTO;
import edu.project.jobportal.entity.Employer;
import edu.project.jobportal.entity.Job;
import edu.project.jobportal.exception.EmployerNotFoundException;
import edu.project.jobportal.util.ResponseStructure;
@Service
public class JobService {

	@Autowired
	private JobDAO jobDAO;
	@Autowired
	private EmployerDAO employerDAO;

	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<ResponseStructure<Job>> saveJob(JobDTO jobDTO,long employerId){
		Employer employer = employerDAO.findEmployer(employerId);
		if(employer!=null){
		
		Job job=this.modelMapper.map(jobDTO, Job.class);
				
//				new Job();
		
//		job.setJobTitle(jobDTO.getJobTitle());
//		job.setJobDescription(jobDTO.getJobDescription());
//		job.setCompany(jobDTO.getCompany());
//		job.setSalary(jobDTO.getSalary());
		
		
		job.setJobCreatedDateTime(LocalDateTime.now());
	
//		job.setJobApplicatons(null);
		           //set employer in job //many to one //Job to Employer
		job.setEmployer(employer);
		Job job2 = jobDAO.saveJob(job);
		           //set list of jobs in employer //one to many //Employer to Job
		employer.getJobs().add(job);
		employerDAO.saveEmployer(employer);
		           
		            //set saved job to jobDTO(which are added implicitly)
		jobDTO.setJobId(job2.getJobId());
		jobDTO.setEmployer(job2.getEmployer());
//		jobDTO.setJobApplicatons(job2.getJobApplicatons());
		
		ResponseStructure<Job> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("created successfully");
		responseStructure.setData(jobDTO);
		
		return new ResponseEntity<ResponseStructure<Job>>(responseStructure,HttpStatus.CREATED);
		}else {
			throw new EmployerNotFoundException("Employer is empty");
		}
		
		
	}
}
