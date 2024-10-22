package com.patitent_induction.demo.service;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.patitent_induction.demo.dto.PatientDTO;
import com.patitent_induction.demo.entities.Piventity;
import com.patitent_induction.demo.repositories.Validrepository;

@Service
public class PatientUpdate {
	
	Logger logger = LoggerFactory.getLogger(PatientUpdate.class);
	
	@Autowired
	private Validrepository validRepo;

	@Autowired
	public ModelMapper modelMapper;
	
	public Optional<Piventity> getPatientDetailsById(Integer patientId){
		logger.info("Inside getPatientDetailsByiD()");
			return validRepo.findById(patientId);
	}
	
	public boolean updatePatientDetails(PatientDTO pdto) {
		logger.info("Inside updatePatientDetails()");
		try {
		Piventity patientUpdatedDetails = this.modelMapper.map(pdto, Piventity.class);
		logger.info("Model Mapper executed in updatePatientDetails()");
		validRepo.save(patientUpdatedDetails);
			logger.info("Patient Details Updated");
			return true;
		}
		catch(Exception ee) {
			logger.info("Patient Details Not Updated");
			return false;
		}
	}
}
