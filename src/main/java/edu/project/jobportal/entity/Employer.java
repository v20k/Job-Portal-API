package edu.project.jobportal.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employerId;
	
	@NotEmpty(message = "Name shouldn't be Empty")
	private String employerName;
	
//	@NotEmpty(message = "Email shouldn't be Empty")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}",message = "invalid email")
    private String employerEmail;
	
//	@NotEmpty(message ="Password shouldn't be Empty")
	@Pattern(regexp = "(?=.*[0-9])+(?=.*[a-z])+(?=.*[A-Z])+(?=.*[@#$%^&+=])+(?=\\S+$).{8,}",message = "minimum 8 characters mandatory(1 upperCase, 1 lowerCase, 1 Special Character,1 number")
	private String employerPassword;
	
	@OneToMany(mappedBy = "employer")
	@JsonIgnore
	private List<Job> jobs;
	
}
