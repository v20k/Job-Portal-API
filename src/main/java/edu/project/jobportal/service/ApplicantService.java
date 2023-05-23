package edu.project.jobportal.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.ApplicantDAO;
import edu.project.jobportal.dto.ApplicantDTO;
import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.util.ResponseStructure;

@Service
public class ApplicantService {

	@Autowired
	private ApplicantDAO applicantDAO;
	@Autowired
	private ModelMapper modelMapper;
	
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
}
