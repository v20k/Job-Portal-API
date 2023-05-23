package edu.project.jobportal.dto;

import java.util.List;

import edu.project.jobportal.entity.JobApplication;
import edu.project.jobportal.entity.Resume;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantDTO {

	private long applicantId;
	private String applicantName;
	private String applicantEmail;
	private long applicantPhNo;
	
	private List<JobApplication> jobApplication;
	
	private Resume resume;
}
