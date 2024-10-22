package com.patitent_induction.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.patitent_induction.demo.dto.PatientDTO;
import com.patitent_induction.demo.entities.Piventity;
import com.patitent_induction.demo.service.PatientUpdate;
import com.patitent_induction.demo.service.Patientinductionservice;
import com.patitent_induction.demo.service.ProcessPatient;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class Picontroller {

	Logger logger = LoggerFactory.getLogger(Picontroller.class);

	@Autowired
	public Patientinductionservice patientService;

	@Autowired
	public PatientUpdate patientUpdate;
	
	@Autowired
	public ProcessPatient processPatientService;

	public static final String RESPONSEKEY1 = "message";
	public static final String RESPONSEKEY2 = "detailsAvailable";
	public static final String RESPONSEMESSAGE1 = "Error";
	
	@PostMapping("/load/patientdata")
	public ResponseEntity<Map<String, String>> patientUpload(@RequestParam("file") MultipartFile file) {
		logger.info("patientUpload method invoked");
		if (this.patientService.save(file)) {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY1, "INDUCTED");
			logger.info("patientUpload method's if part invoked");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		} else {
			Map<String, String> mpp = new HashMap<>();
			mpp.put(RESPONSEKEY1, "FAILED");
			logger.info("patientUpload method's else part invoked");
			return new ResponseEntity<>(mpp, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/load/patient")
	public ResponseEntity<Map<String, String>> getPatientDetails(@RequestBody PatientDTO pdto) {
		logger.info("Into getPatitentDetails");
		Optional<Piventity> patient = patientUpdate.getPatientDetailsById(pdto.getPatientId());
		if (patient.isPresent()) {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY2, "Yes");
			mp.put("name", patient.get().getPatientName());
			mp.put("address", patient.get().getPatientAddress());
			mp.put("DOB", patient.get().getPatientDateofBirth());
			mp.put("email", patient.get().getPatientEmail());
			mp.put("contactNo", patient.get().getPatientContactNumber());
			mp.put("drugId", patient.get().getPatientDrugId());
			mp.put("drugName", patient.get().getPatientDrugName());
			logger.info("Returning positive response to front end");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		} else {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY2, "No");
			logger.info("Returning negative response to front end");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		}
	}

	@PutMapping("/updatepatient")
	public ResponseEntity<Map<String, String>> updatePatient(@RequestBody PatientDTO pdto2) {
		logger.info("Into updatePatitentDetails");
		Optional<Piventity> patient = patientUpdate.getPatientDetailsById(pdto2.getPatientId());
		if (patient.isPresent()) {
			if (patientUpdate.updatePatientDetails(pdto2)) {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEKEY1, "Updated");
				logger.info("Patient Details Updated");
				logger.info("Returning positive response details updated");
				return new ResponseEntity<>(mp, HttpStatus.OK);
			} else {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEKEY1, RESPONSEMESSAGE1);
				logger.info("Returning negative response details not updated");
				return new ResponseEntity<>(mp, HttpStatus.OK);
			}
		} else {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY1, RESPONSEMESSAGE1);
			logger.info("Returning negative response id details not found");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/retrieve/{patientId}")
	public ResponseEntity<Map<String, String>> getPatientData(@PathVariable Integer patientId){
		logger.info("Into getPatientData()");
		Optional<Piventity> patient = patientUpdate.getPatientDetailsById(patientId);
		if (patient.isPresent()) {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY2, "Yes");
			mp.put("name", patient.get().getPatientName());
			mp.put("address", patient.get().getPatientAddress());
			mp.put("DOB", patient.get().getPatientDateofBirth());
			mp.put("email", patient.get().getPatientEmail());
			mp.put("contactNo", patient.get().getPatientContactNumber());
			mp.put("drugId", patient.get().getPatientDrugId());
			mp.put("drugName", patient.get().getPatientDrugName());
			logger.info("Returning positive response to front end");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		} else {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY2, "No");
			logger.info("Returning negative response to front end");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		}
	}
	
	@PutMapping("/processpatient")
	public ResponseEntity<Map<String, String>> processPatient(@RequestBody PatientDTO pdto2) {
		logger.info("Into processPatient");
		Optional<Piventity> patient = patientUpdate.getPatientDetailsById(pdto2.getPatientId());
		if (patient.isPresent()) {
			if (processPatientService.processPatientData(pdto2)) {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEKEY1, "Updated");
				logger.info("Patient Details Updated");
				logger.info("Returning positive response details updated");
				return new ResponseEntity<>(mp, HttpStatus.OK);
			} else {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEKEY1, RESPONSEMESSAGE1);
				logger.info("Returning negative response details not updated");
				return new ResponseEntity<>(mp, HttpStatus.OK);
			}
		} else {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY1, RESPONSEMESSAGE1);
			logger.info("Returning negative response id details not found");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		}
	}
}