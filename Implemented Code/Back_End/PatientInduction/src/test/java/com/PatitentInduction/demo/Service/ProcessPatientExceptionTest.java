package com.PatitentInduction.demo.Service;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.patitent_induction.demo.dto.PatientDTO;
import com.patitent_induction.demo.service.ProcessPatient;

class ProcessPatientExceptionTest {
	
	@Autowired
	public ProcessPatient pProcess = new ProcessPatient();
	
	@Test
	void updatePatientDetailstest1() {
	
	PatientDTO pdto	= new PatientDTO(1, null, "pune", "08-21-2001", "rushi@gmail.com", "7350095218",
				"11111-2222-33", "cipla");
	assertEquals(false, pProcess.processPatientData(pdto));
	}
}
