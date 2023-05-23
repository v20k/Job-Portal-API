package edu.project.jobportal.dao;

import java.util.List;

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
	
}