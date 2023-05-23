package edu.project.jobportal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.jobportal.entity.Employer;
import edu.project.jobportal.repository.EmployerRepo;

@Repository
public class EmployerDAO {

	
	@Autowired
	private EmployerRepo employerRepo;
	
	public Employer saveEmployer(Employer employer){
		return employerRepo.save(employer);
	}
	
	public Employer findEmployer(long employerId) {
		Optional<Employer> optional = employerRepo.findById(employerId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
}
