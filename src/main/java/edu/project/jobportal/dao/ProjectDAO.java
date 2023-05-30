package edu.project.jobportal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.jobportal.entity.Project;
import edu.project.jobportal.repository.ProjectRepo;

@Repository
public class ProjectDAO {

	@Autowired
	private ProjectRepo projectRepo;
	
	public Project  saveProject(Project project) {
		return projectRepo.save(project); 
	}

	public Project deleteProject(long projectId) {
		Optional<Project> optional = projectRepo.findById(projectId);
		if(optional.isEmpty()) {
			return null;
		}else {
			projectRepo.deleteById(projectId);
			return optional.get();
		}
		
		
	}

	public Project findProject(long projectId) {
	       Optional<Project> optional = projectRepo.findById(projectId);
		   if(optional.isEmpty()) {
			   return null;
		   }else {
			   return optional.get();
		   }
	}
	
	
	
}
