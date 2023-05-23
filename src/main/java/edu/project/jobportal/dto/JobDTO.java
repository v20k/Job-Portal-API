package edu.project.jobportal.dto;

import java.util.List;

import edu.project.jobportal.entity.Employer;
import edu.project.jobportal.entity.JobApplication;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDTO {

	private long jobId;
	private String jobTitle;
	private String jobDescription;
	private String company;
	private Double salary;
	
	private Employer employer;
	
	private List<JobApplication> jobApplications;
}
