package edu.project.jobportal.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.project.jobportal.util.ResponseStructure;

@RestControllerAdvice
public class AppException extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ObjectError> errors = ex.getAllErrors();
		Map<String,String> map=new HashMap<>();
		
		for(ObjectError error:errors) {
			String fieldName = ((FieldError)error).getField();
			 String message = error.getDefaultMessage();
			 
			 map.put(fieldName, message);
		}
		
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> hand(EmployerNotFoundException exc){
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(exc.getMessage());
		responseStructure.setData("Employer not found");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handler(ApplicantNotFoundException exc){
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(exc.getMessage());
		responseStructure.setData("Applicant not found");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> resumeHandler(ResumeNotFoundException exc){
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(exc.getMessage());
		responseStructure.setData("Resume not found");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
			}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> jobHandler(JobNotFoundException exc){
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(exc.getMessage());
		responseStructure.setData("Job not found");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
			}
	
}
