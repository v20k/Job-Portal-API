package edu.project.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.project.jobportal.dto.ResumeDTO;
import edu.project.jobportal.entity.Resume;
import edu.project.jobportal.service.ResumeService;
import edu.project.jobportal.util.ResponseStructure;

@RestController
@RequestMapping("/resume")
public class ResumeController {

	@Autowired
	private ResumeService resumeService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Resume>> saveResume(@RequestParam long applicantId, @RequestBody ResumeDTO resumeDTO) {
		
		return resumeService.saveResume(applicantId,resumeDTO);
		
	}
}
