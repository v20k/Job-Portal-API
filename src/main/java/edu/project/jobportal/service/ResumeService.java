package edu.project.jobportal.service;

import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.ApplicantDAO;
import edu.project.jobportal.dao.ProjectDAO;
import edu.project.jobportal.dao.ResumeDAO;
import edu.project.jobportal.dto.ResumeDTO;
import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.entity.Project;
import edu.project.jobportal.entity.Resume;
import edu.project.jobportal.exception.ApplicantNotFoundException;
import edu.project.jobportal.exception.ResumeNotFoundException;
import edu.project.jobportal.util.ResponseStructure;

@Service
public class ResumeService {

	@Autowired
	private ResumeDAO resumeDAO;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApplicantDAO applicantDAO;
	
	@Autowired
	private ProjectDAO projectDAO;
	public ResponseEntity<ResponseStructure<Resume>> saveResume(long applicantId, ResumeDTO resumeDTO){
		
		Applicant applicant = applicantDAO.findApplicant(applicantId);
		
		if(applicant!=null) {
		
		Resume resume = this.modelMapper.map(resumeDTO, Resume.class);
		
		resume.setApplicant(applicant);
		Resume existingresume = applicant.getResume();
		if(existingresume!=null) {
			resume.setResumeId(existingresume.getResumeId());
		}
		Resume resume2 = resumeDAO.saveResume(resume);
		
		applicant.setResume(resume);
		applicantDAO.saveApplicant(applicant);
		
		ResponseStructure<Resume> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Created successfully");
		responseStructure.setData(resume2);
		
		return new ResponseEntity<ResponseStructure<Resume>>(responseStructure,HttpStatus.CREATED);
		}else {
			throw new ApplicantNotFoundException("Applicant is Empty");
		}
		}
	
	
public ResponseEntity<ResponseStructure<Resume>> deleteResume(long resumeId){
	      
	       Resume resume = resumeDAO.findResume(resumeId);	
	
			/* Applicant applicant = applicantDAO.findApplicant(applicantId); */
		
		if(resume!=null) {
			Applicant applicant = resume.getApplicant();
			applicant.setResume(null);
			
			resume.setSkills(null);
			
			List<Project> projects = resume.getProjects();
		     Iterator<Project> projectIterator = projects.iterator();
			while(projectIterator.hasNext()) {
				Project project = projectIterator.next();
				projectIterator.remove();
				projectDAO.deleteProject(project.getProjectId());
			}
			resumeDAO.deleteResume(resumeId);
			ResponseStructure<Resume> responseStructure=new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("deleted successfully");
			responseStructure.setData(resume);
			
			return new ResponseEntity<ResponseStructure<Resume>>(responseStructure,HttpStatus.CREATED);
			
		}else {
			throw new ResumeNotFoundException("Resume is Empty");
		}
}
	
	
}
