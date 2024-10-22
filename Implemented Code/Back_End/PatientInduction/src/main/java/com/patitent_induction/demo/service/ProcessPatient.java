package com.patitent_induction.demo.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.patitent_induction.demo.dto.PatientDTO;
import com.patitent_induction.demo.entities.ProcessedEntity;
import com.patitent_induction.demo.repositories.ProcessedRepository;
import com.patitent_induction.demo.repositories.Validrepository;

@Service
public class ProcessPatient {

Logger logger = LoggerFactory.getLogger(ProcessPatient.class);
	
	@Autowired
	private Validrepository validRepo;

	@Autowired
	private ProcessedRepository processRepo;
	
	@Autowired
	public ModelMapper modelMapper;
	
	public boolean processPatientData(PatientDTO pdto) {
		logger.info("Inside processPatientData()");
		try {
		ProcessedEntity processDetails = this.modelMapper.map(pdto, ProcessedEntity.class);
		logger.info("Model Mapper executed in updatePatientDetails()");
		validRepo.deleteById(pdto.getPatientId());
		logger.info("Patient Details deleted to inducted_record");
		processRepo.save(processDetails);
			logger.info("Patient Details saved to processed_record");
			return true;
		}
		catch(Exception ee) {
			logger.info("Patient Details Not Updated");
			return false;
		}
	}
}
