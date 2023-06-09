package edu.project.jobportal.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.project.jobportal.entity.Skill;
import edu.project.jobportal.repository.SkillRepo;

@Repository
public class SkillDAO {

	@Autowired
	private SkillRepo skillRepo;
	
	public Skill saveSkill(Skill skill) {
		
		return skillRepo.save(skill);
		
	}

	public Skill getSkillByName(String skill) {
	  Optional<Skill> optional = skillRepo.getSkillByName(skill);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	
}
