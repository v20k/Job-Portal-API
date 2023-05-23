package edu.project.jobportal.dto;

import java.util.List;

import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.entity.Project;
import edu.project.jobportal.entity.Skill;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeDTO {

	private long resumeId;
	private String summary;
	private String qualification;
	private String university;
	private String socialProfile1;
	private String socialProfile2;
	private String socialProfile3;
	private String certification;
	
	private Applicant applicant;
	
	private List<Skill> skills;
	
	private List<Project> projects;
	
}
