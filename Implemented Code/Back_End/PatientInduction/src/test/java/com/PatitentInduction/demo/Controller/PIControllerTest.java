package com.PatitentInduction.demo.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import com.patitent_induction.demo.controller.Picontroller;
import com.patitent_induction.demo.dto.PatientDTO;
import com.patitent_induction.demo.entities.Pinventity;
import com.patitent_induction.demo.entities.Piventity;
import com.patitent_induction.demo.service.PatientUpdate;
import com.patitent_induction.demo.service.Patientinductionservice;
import com.patitent_induction.demo.service.ProcessPatient;

@ExtendWith(MockitoExtension.class)
class PIControllerTest {

	@InjectMocks
	public Picontroller controller;

	@Mock
	public Patientinductionservice mockService;

	@Mock
	public PatientUpdate mockUpdate;
	
	@Mock
	public ProcessPatient mockProcess;
	
	MultipartFile multipartFile = new MockMultipartFile("file","PatientInduction.xlsx","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "13 rushikesh pune 8-21-01 rushi@gmail.com 7350095218 11111-2222-33 cipla".getBytes());

	@Test
	void checkController1() throws IOException{
		
		//given
		Map<String, String> mp = new HashMap<>();
		mp.put("message", "INDUCTED");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp,HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp,HttpStatus.OK);
		//when
		when(mockService.save(multipartFile)).thenReturn(true);
		mppRE = controller.patientUpload(multipartFile);
		//then
		assertEquals(mpRE.toString(),mppRE.toString());
	}
	
	@Test
	void checkController2() throws IOException{
		
		//given
		Map<String, String> mp = new HashMap<>();
		mp.put("message", "FAILED");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp,HttpStatus.BAD_REQUEST);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp,HttpStatus.BAD_REQUEST);
		//when
		when(mockService.save(multipartFile)).thenReturn(false);
		mppRE = controller.patientUpload(multipartFile);
		//then
		assertEquals(mpRE.toString(),mppRE.toString());
	}

	
	//testing getPatientDetails() of controller class
	@Test
	void getPatientDetailsTest(){
		
		Optional<Piventity> patient = Optional.ofNullable(new Piventity(1, "rushikesh", "pune", "08-21-2001", "rushi@gmail.com", "735009521",
				"11111-2222-33", "cipla"));
		Map<String, String> mp = new HashMap<>();
		mp.put("detailsAvailable", "Yes");
		mp.put("name", patient.get().getPatientName());
		mp.put("address", patient.get().getPatientAddress());
		mp.put("DOB", patient.get().getPatientDateofBirth());
		mp.put("email", patient.get().getPatientEmail());
		mp.put("contactNo", patient.get().getPatientContactNumber());
		mp.put("drugId", patient.get().getPatientDrugId());
		mp.put("drugName", patient.get().getPatientDrugName());
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp,HttpStatus.OK);
		PatientDTO pdto = new PatientDTO(1, null, null, null, null, null, null, null);
		when(mockUpdate.getPatientDetailsById(pdto.getPatientId())).thenReturn(patient);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp,HttpStatus.OK);
		mppRE = controller.getPatientDetails(pdto);
		assertEquals(mpRE.toString(),mppRE.toString());
	}
	
	@Test
	void getPatientDetailsTest2(){
		
		Map<String, String> mp = new HashMap<>();
		mp.put("detailsAvailable", "No");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp,HttpStatus.OK);
		PatientDTO pdto = new PatientDTO(1, null, null, null, null, null, null, null);
		when(mockUpdate.getPatientDetailsById(pdto.getPatientId())).thenReturn(Optional.empty());
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp,HttpStatus.OK);
		mppRE = controller.getPatientDetails(pdto);
		assertEquals(mpRE.toString(),mppRE.toString());
	}
	
	
	//updatePatient() test cases
	@Test
	void updatePatient1() {
		Map<String, String> mp = new HashMap<>();
		mp.put("message", "Error");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp,HttpStatus.OK);
		PatientDTO pdto = new PatientDTO(1, null, null, null, null, null, null, null);
		when(mockUpdate.getPatientDetailsById(pdto.getPatientId())).thenReturn(Optional.empty());
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp,HttpStatus.OK);
		mppRE = controller.updatePatient(pdto);
		assertEquals(mpRE.toString(),mppRE.toString());
	}
	
	@Test
	void updatePatient2() {
		Map<String, String> mp = new HashMap<>();
		mp.put("message", "Error");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp,HttpStatus.OK);
		PatientDTO pdto = new PatientDTO(1, null, null, null, null, null, null, null);
		Optional<Piventity> patient = Optional.ofNullable(new Piventity(1, "rushikesh", "pune", "08-21-2001", "rushi@gmail.com", "735009521",
				"11111-2222-33", "cipla"));
		when(mockUpdate.getPatientDetailsById(pdto.getPatientId())).thenReturn(patient);
		when(mockUpdate.updatePatientDetails(pdto)).thenReturn(false);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp,HttpStatus.OK);
		mppRE = controller.updatePatient(pdto);
		assertEquals(mpRE.toString(),mppRE.toString());
	}
	
	@Test
	void updatePatient3() {
		Map<String, String> mp = new HashMap<>();
		mp.put("message", "Updated");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp,HttpStatus.OK);
		Optional<Piventity> patient = Optional.ofNullable(new Piventity(1, "rushikesh", "pune", "08-21-2001", "rushi@gmail.com", "735009521",
				"11111-2222-33", "cipla"));
		PatientDTO pdto = new PatientDTO(1, null, null, null, null, null, null, null);
		when(mockUpdate.getPatientDetailsById(pdto.getPatientId())).thenReturn(patient);
		when(mockUpdate.updatePatientDetails(pdto)).thenReturn(true);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp,HttpStatus.OK);
		mppRE = controller.updatePatient(pdto);
		assertEquals(mpRE.toString(),mppRE.toString());
	}
	
	
	// getPatientData()
	@Test
	void  getPatientDataTest1() {
		
		Map<String, String> mp = new HashMap<>();
		mp.put("detailsAvailable", "No");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp,HttpStatus.OK);
		when(mockUpdate.getPatientDetailsById(1)).thenReturn(Optional.empty());
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp,HttpStatus.OK);
		mppRE = controller.getPatientData(1);
		assertEquals(mpRE.toString(),mppRE.toString());
	}
	
	@Test
	void  getPatientDataTest2() {
		
		Optional<Piventity> patient = Optional.ofNullable(new Piventity(1, "rushikesh", "pune", "08-21-2001", "rushi@gmail.com", "735009521",
				"11111-2222-33", "cipla"));
		Map<String, String> mp = new HashMap<>();
		mp.put("detailsAvailable", "Yes");
		mp.put("name", patient.get().getPatientName());
		mp.put("address", patient.get().getPatientAddress());
		mp.put("DOB", patient.get().getPatientDateofBirth());
		mp.put("email", patient.get().getPatientEmail());
		mp.put("contactNo", patient.get().getPatientContactNumber());
		mp.put("drugId", patient.get().getPatientDrugId());
		mp.put("drugName", patient.get().getPatientDrugName());
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp,HttpStatus.OK);
		when(mockUpdate.getPatientDetailsById(1)).thenReturn(patient);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp,HttpStatus.OK);
		mppRE = controller.getPatientData(1);
		assertEquals(mpRE.toString(),mppRE.toString());
	}
	
	//processPatient() test cases
	@Test
	void processPatient1() {
		Map<String, String> mp = new HashMap<>();
		mp.put("message", "Error");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp,HttpStatus.OK);
		PatientDTO pdto = new PatientDTO(1, null, null, null, null, null, null, null);
		when(mockUpdate.getPatientDetailsById(pdto.getPatientId())).thenReturn(Optional.empty());
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp,HttpStatus.OK);
		mppRE = controller.processPatient(pdto);
		assertEquals(mpRE.toString(),mppRE.toString());
	}
	
	@Test
	void processPatient2() {
		Map<String, String> mp = new HashMap<>();
		mp.put("message", "Error");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp,HttpStatus.OK);
		PatientDTO pdto = new PatientDTO(1, null, null, null, null, null, null, null);
		Optional<Piventity> patient = Optional.ofNullable(new Piventity(1, "rushikesh", "pune", "08-21-2001", "rushi@gmail.com", "735009521",
				"11111-2222-33", "cipla"));
		when(mockUpdate.getPatientDetailsById(pdto.getPatientId())).thenReturn(patient);
		when(mockProcess.processPatientData(pdto)).thenReturn(false);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp,HttpStatus.OK);
		mppRE = controller.processPatient(pdto);
		assertEquals(mpRE.toString(),mppRE.toString());
	}
	
	@Test
	void processPatient3() {
		Map<String, String> mp = new HashMap<>();
		mp.put("message", "Updated");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp,HttpStatus.OK);
		Optional<Piventity> patient = Optional.ofNullable(new Piventity(1, "rushikesh", "pune", "08-21-2001", "rushi@gmail.com", "735009521",
				"11111-2222-33", "cipla"));
		PatientDTO pdto = new PatientDTO(1, null, null, null, null, null, null, null);
		when(mockUpdate.getPatientDetailsById(pdto.getPatientId())).thenReturn(patient);
		when(mockProcess.processPatientData(pdto)).thenReturn(true);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp,HttpStatus.OK);
		mppRE = controller.processPatient(pdto);
		assertEquals(mpRE.toString(),mppRE.toString());
	}
}
