package edu.project.jobportal.service;

import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.EmployerDAO;
import edu.project.jobportal.dto.EmployerDTO;
import edu.project.jobportal.entity.Employer;
import edu.project.jobportal.entity.Job;
import edu.project.jobportal.exception.EmployerNotFoundException;
import edu.project.jobportal.util.ResponseStructure;


@Service
public class EmployerService {

	
	@Autowired
	private EmployerDAO employerDAO;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<ResponseStructure<Employer>> saveEmployer(Employer employer){
		Employer employer2 = employerDAO.saveEmployer(employer);
		
		EmployerDTO employerDTO=new EmployerDTO();
		employerDTO.setEmployerId(employer2.getEmployerId());
		employerDTO.setEmployerName(employer2.getEmployerName());
		employerDTO.setEmployerEmail(employer2.getEmployerEmail());
		employerDTO.setJobs(employer2.getJobs());
		
		ResponseStructure<Employer> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Created Successfully");
		responseStructure.setData(employerDTO);
		
		return new ResponseEntity<ResponseStructure<Employer>>(responseStructure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Employer>> findEmployer(long employerId) {
	
		Employer employer2 = employerDAO.findEmployer(employerId);
		if(employer2!=null) {
			EmployerDTO employerDTO=new EmployerDTO();
			employerDTO.setEmployerId(employer2.getEmployerId());
			employerDTO.setEmployerName(employer2.getEmployerName());
			employerDTO.setEmployerEmail(employer2.getEmployerEmail());
			employerDTO.setJobs(employer2.getJobs());
			
			ResponseStructure<Employer> responseStructure=new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Fetched Successfully");
			responseStructure.setData(employerDTO);
			
			return new ResponseEntity<ResponseStructure<Employer>>(responseStructure,HttpStatus.FOUND);
			
		}else {
			throw new EmployerNotFoundException("Employer is Empty");
		}
			
	}
	
	public ResponseEntity<ResponseStructure<Employer>> deleteEmployer(long employerId){
		Employer employer = employerDAO.findEmployer(employerId);
		if(employer!=null) {
			List<Job> jobs = employer.getJobs();
			Iterator<Job> jobIterator = jobs.iterator();
			while(jobIterator.hasNext()) {
				Job job = jobIterator.next();
				job.setEmployer(null);
			}
			employerDAO.deleteEmployer(employerId);
			
             EmployerDTO employerDTO = this.modelMapper.map(employer, EmployerDTO.class);
			ResponseStructure<Employer> responseStructure=new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted Successfully");
			responseStructure.setData(employerDTO);
			
			return new ResponseEntity<ResponseStructure<Employer>>(responseStructure,HttpStatus.OK);
			
		}else {
			throw new EmployerNotFoundException("Employer is Empty");
		}
	}
	
	
}
