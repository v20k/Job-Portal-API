package edu.project.jobportal;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobportalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobportalApplication.class, args);
	}

	//testing 
	//testing 2
	
	//testing 4
	//testing 5
	//testing 6
	//testing 7
	@Bean
	public ModelMapper getMM() {
		return new ModelMapper();
	}
}
