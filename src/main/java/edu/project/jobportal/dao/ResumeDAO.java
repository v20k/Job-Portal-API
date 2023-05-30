package edu.project.jobportal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.jobportal.entity.Resume;
import edu.project.jobportal.repository.ResumeRepo;

@Repository
public class ResumeDAO {

	@Autowired
	private ResumeRepo resumeRepo;
	
	public Resume saveResume(Resume resume) {
		return resumeRepo.save(resume);
	}

	public Resume deleteResume(long resumeId) {
		Optional<Resume> optional = resumeRepo.findById(resumeId);
		
		if(optional.isEmpty()) {
			return null;
		}else {
		resumeRepo.deleteById(resumeId);
		return optional.get();
		}
	}

	public Resume findResume(long resumeId) {
		Optional<Resume> optional = resumeRepo.findById(resumeId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	
	
	
	
}
