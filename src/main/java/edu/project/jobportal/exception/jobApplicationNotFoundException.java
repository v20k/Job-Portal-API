package edu.project.jobportal.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@AllArgsConstructor
@Getter
public class jobApplicationNotFoundException extends RuntimeException {

	private String message;
	
}
