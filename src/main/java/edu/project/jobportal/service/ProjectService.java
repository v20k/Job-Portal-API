package edu.project.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.ApplicantDAO;
import edu.project.jobportal.dao.ProjectDAO;
import edu.project.jobportal.dao.ResumeDAO;
import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.entity.Project;
import edu.project.jobportal.entity.Resume;
import edu.project.jobportal.exception.ApplicantNotFoundException;
import edu.project.jobportal.exception.ResumeNotFoundException;
import edu.project.jobportal.util.ResponseStructure;

@Service
public class ProjectService {

	@Autowired
	private ProjectDAO projectDAO;
	
	@Autowired
	private ApplicantDAO applicantDAO;
	@Autowired
	private ResumeDAO resumeDAO;
	
	public ResponseEntity<ResponseStructure<Resume>> saveProject(long applicantId,Project project){
		Applicant applicant = applicantDAO.findApplicant(applicantId);
		
		if(applicant!=null) {
			Resume existingResume = applicant.getResume();
			if(existingResume!=null) {
				
				
				
				Project project2 = projectDAO.saveProject(project);
				
				existingResume.getProjects().add(project2);
				Resume resume2 = resumeDAO.saveResume(existingResume);
				
				ResponseStructure<Resume> responseStructure=new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Created Successfully");
				responseStructure.setData(resume2);
				
				return new ResponseEntity<ResponseStructure<Resume>>(responseStructure,HttpStatus.CREATED);
				
			}else {
				throw new ResumeNotFoundException("Resume is Empty");
				}
			
		}else {
			throw new ApplicantNotFoundException("applicant is empty");
		}
		
		
		
		
		
		
	}
}
