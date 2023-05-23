package edu.project.jobportal.service;

import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.ApplicantDAO;
import edu.project.jobportal.dao.JobApplicationDAO;
import edu.project.jobportal.dto.ApplicantDTO;
import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.entity.JobApplication;
import edu.project.jobportal.exception.ApplicantNotFoundException;
import edu.project.jobportal.util.ResponseStructure;

@Service
public class ApplicantService {

	@Autowired
	private ApplicantDAO applicantDAO;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private JobApplicationDAO jobApplicationDAO;
	
	public ResponseEntity<ResponseStructure<Applicant>> saveApplicant(Applicant applicant){

			applicantDAO.saveApplicant(applicant);
		    ApplicantDTO applicantDTO=this.modelMapper.map(applicant, ApplicantDTO.class);
//		    =new ApplicantDTO();
//		    
//		    applicantDTO.setApplicantId(applicant.getApplicantId());
//		    applicantDTO.setApplicantName(applicant.getApplicantName());
//			applicantDTO.setApplicantEmail(applicant.getApplicantEmail());
//			applicantDTO.setApplicantPhNo(applicant.getApplicantPhNo());
			
			ResponseStructure<Applicant> responseStructure=new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Created successfully");
			responseStructure.setData(applicantDTO);
			return new ResponseEntity<ResponseStructure<Applicant>>(responseStructure,HttpStatus.CREATED);
			
		
		
	}

	public ResponseEntity<ResponseStructure<Applicant>> findApplicant(long applicantId) {
		Applicant applicant = applicantDAO.findApplicant(applicantId);
		if(applicant!=null) {
			ApplicantDTO applicantDTO = this.modelMapper.map(applicant, ApplicantDTO.class);
			ResponseStructure<Applicant> responseStructure=new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("fetched successfully");
			responseStructure.setData(applicantDTO);
			
			return new ResponseEntity<ResponseStructure<Applicant>>(responseStructure,HttpStatus.FOUND);
		}else {
			throw new ApplicantNotFoundException("Applicant is Empty");
		}
	}

	public ResponseEntity<ResponseStructure<Applicant>> updateApplicant(long applicantId, Applicant applicant) {
		Applicant applicant2 = applicantDAO.findApplicant(applicantId);
		if(applicant2!=null) {
			applicant.setApplicantId(applicant2.getApplicantId());
			applicant.setJobApplication(applicant2.getJobApplication());
			applicant.setResume(applicant2.getResume());
			
			applicantDAO.saveApplicant(applicant);
			
			ApplicantDTO applicantDTO = this.modelMapper.map(applicant,ApplicantDTO.class);
			
			ResponseStructure<Applicant> responseStructure=new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("updated successfully");
			responseStructure.setData(applicantDTO);
			return new ResponseEntity<ResponseStructure<Applicant>>(responseStructure,HttpStatus.CREATED);
		}else {
			throw new ApplicantNotFoundException("Applicant is Empty");
		}
	}

	public ResponseEntity<ResponseStructure<Applicant>> deleteApplicant(long applicantId) {
		Applicant applicant2 = applicantDAO.findApplicant(applicantId);
		if(applicant2!=null) {
			
			
			applicant2.setJobApplication(null);
			applicant2.setResume(null);
			
			applicantDAO.saveApplicant(applicant2);
			//
			List<JobApplication> jobApplications = jobApplicationDAO.getAllJobApplication();
			Iterator<JobApplication> iterator = jobApplications.iterator();
			while(iterator.hasNext()) {
				JobApplication value = iterator.next();
				if(value.getApplicant()==applicant2) {
					jobApplicationDAO.deleteJobApplicantion(value.getJobApplicationId());
				}
			}
			
//			jobApplication.setApplicant(null);
//			jobApplicationDAO.saveJobApplication(jobApplication);
			
			//
			ApplicantDTO applicantDTO = this.modelMapper.map(applicant2,ApplicantDTO.class);
			
			applicantDAO.deleteApplicant(applicantId);
			
			ResponseStructure<Applicant> responseStructure=new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("deleted successfully");
			responseStructure.setData(applicantDTO);
			return new ResponseEntity<ResponseStructure<Applicant>>(responseStructure,HttpStatus.CREATED);
		}else {
			throw new ApplicantNotFoundException("Applicant is Empty");
		}	
	}
}
