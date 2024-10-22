package com.patitent_induction.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientDTO {

	public PatientDTO() {
		// empty constructor
	}

	private Integer patientId;

	private String patientName;

	private String patientAddress;

	private String patientDateofBirth;

	private String patientEmail;

	private String patientContactNumber;

	private String patientDrugId;

	private String patientDrugName;
}
