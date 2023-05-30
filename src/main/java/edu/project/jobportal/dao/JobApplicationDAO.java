package edu.project.jobportal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.jobportal.entity.JobApplication;
import edu.project.jobportal.repository.JobApplicationRepo;


@Repository
public class JobApplicationDAO {

	@Autowired
	private JobApplicationRepo jobApplicationRepo;
	
	public JobApplication saveJobApplication(JobApplication jobApplication) {
		return jobApplicationRepo.save(jobApplication);
	}

	public List <JobApplication> getAllJobApplication() {
		return jobApplicationRepo.getAllJobApplication();
		
	}

	public JobApplication deleteJobApplicantion(long jobApplicationId) {
		Optional<JobApplication> optional = jobApplicationRepo.findById(jobApplicationId);
		if(optional.isEmpty()) {
			return null;
		}else {
			JobApplication jobApplication = optional.get();
		jobApplicationRepo.deleteById(jobApplicationId);
		return jobApplication;
		}
	}

	public JobApplication findJobApplication(long jobApplicationId) {
	    Optional<JobApplication> optional = jobApplicationRepo.findById(jobApplicationId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	    
	}
	
}
