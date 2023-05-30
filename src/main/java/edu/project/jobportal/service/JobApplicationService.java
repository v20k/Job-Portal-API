package edu.project.jobportal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.ApplicantDAO;
import edu.project.jobportal.dao.JobApplicationDAO;
import edu.project.jobportal.dao.JobDAO;
import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.entity.Job;
import edu.project.jobportal.entity.JobApplication;
import edu.project.jobportal.exception.ApplicantNotFoundException;
import edu.project.jobportal.exception.JobNotFoundException;
import edu.project.jobportal.exception.jobApplicationNotFoundException;
import edu.project.jobportal.util.ResponseStructure;

@Service
public class JobApplicationService {

	@Autowired
	private JobApplicationDAO jobApplicationDAO;
	
	@Autowired
	private JobDAO jobDAO;
	
	@Autowired
	private ApplicantDAO applicantDAO;
	
	public ResponseEntity<ResponseStructure<JobApplication>> saveJobApplication(long jobId,long applicantId){
		Applicant applicant = applicantDAO.findApplicant(applicantId);
		if(applicant!=null) {
			Job job = jobDAO.findJob(jobId);
		if(job!=null) {
			
		JobApplication jobApplication=new JobApplication();
		jobApplication.setJobApplicationDateTime(LocalDateTime.now());
		//
		List<JobApplication> jobApplications = jobApplicationDAO.getAllJobApplication();
		for(JobApplication jA:jobApplications) {
		  Applicant app = jA.getApplicant();
		  long appId = app.getApplicantId();
		  Job j = jA.getJob();
		  long jId = j.getJobId();
		  if((appId==applicant.getApplicantId()&&(jId==job.getJobId()))){
			  jobApplication.setJobApplicationId(jA.getJobApplicationId());
			  
		  }
		}
//		
//		if(jobApplications.contains(job.getJobApplicatons())) {
//			if(jobApplications.contains())
//		jobApplication.setJobApplicationId();
//		}
		//
		jobApplication.setJob(job);
		jobApplication.setApplicant(applicant);
		JobApplication jobApplication2 = jobApplicationDAO.saveJobApplication(jobApplication);
		
		applicant.getJobApplication().add(jobApplication2);
		applicantDAO.saveApplicant(applicant);
		
		job.getJobApplicatons().add(jobApplication2);
		jobDAO.saveJob(job);
		
		ResponseStructure<JobApplication> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Created Successfully");
		responseStructure.setData(jobApplication2);
		return new ResponseEntity<ResponseStructure<JobApplication>>(responseStructure,HttpStatus.CREATED);
		}else {
			throw new JobNotFoundException("Job is Empty");
		}
		
		}else {
			throw new ApplicantNotFoundException("Applicant is Empty");
		}
		
		}
	
	public ResponseEntity<ResponseStructure<JobApplication>> deleteJobApplication(long jobApplicationId){
		JobApplication jobApplication = jobApplicationDAO.findJobApplication(jobApplicationId);
        if(jobApplication!=null) {
        	jobApplication.setJob(null);
        	jobApplication.setApplicant(null);
        	
        	jobApplicationDAO.deleteJobApplicantion(jobApplicationId);
        	
        	ResponseStructure<JobApplication> responseStructure=new ResponseStructure<>();
    		responseStructure.setStatusCode(HttpStatus.OK.value());
    		responseStructure.setMessage("Deleted Successfully");
    		responseStructure.setData(jobApplication);
    		return new ResponseEntity<ResponseStructure<JobApplication>>(responseStructure,HttpStatus.OK);
    	
        }else {
        	throw new jobApplicationNotFoundException("JobApplication is Empty");
        }
	
	}
	
	
	
}
