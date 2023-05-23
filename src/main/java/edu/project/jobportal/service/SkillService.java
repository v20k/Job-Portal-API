package edu.project.jobportal.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.project.jobportal.dao.ApplicantDAO;
import edu.project.jobportal.dao.ResumeDAO;
import edu.project.jobportal.dao.SkillDAO;
import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.entity.Resume;
import edu.project.jobportal.entity.Skill;
import edu.project.jobportal.exception.ApplicantNotFoundException;
import edu.project.jobportal.exception.ResumeNotFoundException;
import edu.project.jobportal.util.ResponseStructure;

@Service
public class SkillService {

	@Autowired
	private SkillDAO skillDAO;
	
	@Autowired
	private ApplicantDAO applicantDAO;
	
	@Autowired
	private ResumeDAO resumeDAO;
	
	public ResponseEntity<ResponseStructure<Resume>> saveSkill(long applicantId,String[] skills){
	
		Applicant applicant2 = applicantDAO.findApplicant(applicantId);
		 Resume resume3=null;
		 List<Skill> ma=null;
		if(applicant2!=null) {
			Resume resume = applicant2.getResume();
			if(resume!=null) {
				List<Skill> existingSkillsInResume = resume.getSkills();     //getting all the skills which are already joined with resume.    InCase if it is new, it is null
				ma = existingSkillsInResume;
				System.out.println("applicant and resume are present--------------------------------");
				
			 for(String skill:skills) {                                                                   //iterating over the provided skills in @request param(parameter)
				 Skill existingSkillInDatabase = skillDAO.getSkillByName(skill);                          //checking whether the skill is already in database
				 if(existingSkillInDatabase!=null) {                               //if the skill is already present in database 
					 if(!(existingSkillsInResume.contains(existingSkillInDatabase))) {                    //if (java,HTML).contains(java)=true -- !true=false --loop closes
						                                                                                  //if (java,HTML).contains(js)=false  -- !false=true --loop opens
						 System.out.println(existingSkillsInResume+"-----------------"+existingSkillInDatabase+"existing skill in Resume is not present in existing database-------------------------------------");
						 existingSkillsInResume.add(existingSkillInDatabase);
						 System.out.println("object(skill) which is already in database but not in resume is to be added into the resume------------------------------------");
					 }
				 }else {                                                          //if skill is not already present in database
					 Skill newSkill=new Skill();
					 newSkill.setSkillName(skill);
					 Skill skill4 = skillDAO.saveSkill(newSkill);
					 System.out.println(skill4+"new skill is added into the database-----------------------------------------");
					 existingSkillsInResume.add(skill4);
				 }
				 
				 
				
			 }
			// 
//			 List<String> ski = Arrays.asList(skills);
//			 Iterator<String> iterator = ski.iterator();
//			 while(iterator.hasNext()) {
//				 String value = iterator.next();
//				 if(!(existingSkillsInResume.contains(value))){
//					 existingSkillsInResume.remove(value);
//				 }
//				 
//			 }
			 if(ma!=null) {
			 List<String> skil = Arrays.asList(skills);
			 Iterator<Skill> iterator1 = existingSkillsInResume.iterator();
			 while(iterator1.hasNext()) {
				 Skill value = iterator1.next();
				 System.out.println(skil+"================"+value.getSkillName()+"===================================");
				 String val = value.getSkillName();
				 if(!(skil.contains(val))){
					 System.out.println("11111111111111111111");
//					 existingSkillsInResume.remove(value);
					 iterator1.remove();
					 
				 }
				 
			 }
			 System.out.println(skil+"----------------------");
			 }
			 //
			 resume.setSkills(existingSkillsInResume);   
			 resume3 = resumeDAO.saveResume(resume);
	
			 
			 
	ResponseStructure<Resume> responseStructure=new ResponseStructure<>();
	responseStructure.setStatusCode(HttpStatus.CREATED.value());
	responseStructure.setMessage("Created Successfully");
	responseStructure.setData(resume3);
	
	return new ResponseEntity<ResponseStructure<Resume>>(responseStructure,HttpStatus.CREATED);
			}else {
			 throw new ResumeNotFoundException("Resume is empty");	
			}
			}else {
		
			throw new ApplicantNotFoundException("Applicant is empty");
		}
		}
	
}
