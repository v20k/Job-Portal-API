package edu.project.jobportal.dto;

import java.util.List;

import edu.project.jobportal.entity.Job;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployerDTO {
	private long employerId;
	private String employerName;
	private String employerEmail;
	
	private List<Job> jobs;
}
