package com.patitent_induction.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "Processed_Records")
@Data
@AllArgsConstructor
public class ProcessedEntity {

	public ProcessedEntity() {
		// Empty constructor
	}

	@Id
	@Column(name = "patientId")
	private Integer patientId;

	@Column(name = "patientName")
	private String patientName;
	
	@Column(name = "patientAddress")
	private String patientAddress;

	@Column(name = "patientDateofBirth")
	private String patientDateofBirth;

	@Column(name = "patientEmail")
	private String patientEmail;

	@Column(name = "patientContactNumber")
	private String patientContactNumber;

	@Column(name = "patientDrugId")
	private String patientDrugId;

	@Column(name = "patientDrugName")
	private String patientDrugName;
}
