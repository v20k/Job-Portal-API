package edu.project.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.project.jobportal.entity.JobApplication;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Long>{

	@Query(value = "select j from JobApplication j")
   public List<JobApplication> getAllJobApplication();

}
