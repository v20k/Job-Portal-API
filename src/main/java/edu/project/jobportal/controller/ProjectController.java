package edu.project.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.jobportal.entity.Project;
import edu.project.jobportal.entity.Resume;
import edu.project.jobportal.service.ProjectService;
import edu.project.jobportal.util.ResponseStructure;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	@PostMapping
	public ResponseEntity<ResponseStructure<Resume>> saveProject(@RequestParam long applicantId,@RequestBody Project project){
		return projectService.saveProject(applicantId, project);
	}
	
}
