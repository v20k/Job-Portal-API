package edu.project.jobportal.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobId;
	private String jobTitle;
	private String jobDescription;
	private String company;
	private Double salary;
	private LocalDateTime jobCreatedDateTime;
	
	@ManyToOne
	@JoinColumn
	private Employer employer;
	
	@OneToMany(mappedBy = "job")
	@JsonIgnore
	private List<JobApplication> jobApplicatons;
	
	
}
