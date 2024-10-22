package com.patitent_induction.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatientInductionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientInductionApplication.class, args);
	}

@Bean
public ModelMapper modelMapper() {
	return new ModelMapper();
} 
}
