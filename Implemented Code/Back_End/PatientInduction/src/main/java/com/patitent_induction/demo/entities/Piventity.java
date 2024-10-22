package com.patitent_induction.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "Inducted_Records")
@Data
@AllArgsConstructor
public class Piventity {

	public Piventity() {
		//Empty Construction
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patientId")
	private Integer patientId;


	@Column(name = "patientName")
	@NotBlank
	@Size(min = 5, max = 30, message="Patient name must be 5 chars to max 30")
	private String patientName;
	
	@Column(name = "patientAddress")
	@NotBlank(message = "Patient Address should not be empty or null")
	private String patientAddress;

	@Column(name = "patientDateofBirth")
	@NotBlank(message = "Patient DOBshould not be empty or null")
	private String patientDateofBirth;

	@Column(name = "patientEmail")
	@Email(message = "Patient Email should be valid email")
	private String patientEmail;

	@Column(name = "patientContactNumber")
	@Pattern(regexp = "[6-9]\\d{9}")
	private String patientContactNumber;

	@Column(name = "patientDrugId")
	@NotBlank(message = "Patient drug id should be valid")
	@Pattern(regexp = "\\d{5}-\\d{4}-\\d{2}")
	private String patientDrugId;

	@Column(name = "patientDrugName")
	@NotBlank(message = "Patient drug name should be valid")
	private String patientDrugName;

}
