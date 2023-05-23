package edu.project.jobportal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.jobportal.entity.Applicant;
import edu.project.jobportal.repository.ApplicantRepo;

@Repository
public class ApplicantDAO {

	@Autowired
	private ApplicantRepo applicantRepo;
	
	public Applicant saveApplicant(Applicant applicant) {
		return applicantRepo.save(applicant);
	}

	public Applicant findApplicant(long employerId) {
	
		Optional<Applicant> optional= applicantRepo.findById(employerId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
}
