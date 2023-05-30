package edu.project.jobportal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.jobportal.entity.Job;
import edu.project.jobportal.repository.JobRepo;

@Repository
public class JobDAO {

	@Autowired
	private JobRepo jobRepo;
	
	public Job saveJob(Job job) {
		return jobRepo.save(job);
	}
	
	public Job findJob(long jobId) {
		Optional<Job> optional = jobRepo.findById(jobId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}

	public Job deleteJob(long jobId) {
		Optional<Job> optional = jobRepo.findById(jobId);
		if(optional.isEmpty()) {
			return null;
		}else {
			jobRepo.deleteById(jobId);
			return optional.get();
		}
	}
}
