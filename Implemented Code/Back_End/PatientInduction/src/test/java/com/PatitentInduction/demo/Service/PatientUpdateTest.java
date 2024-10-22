package com.PatitentInduction.demo.Service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import com.patitent_induction.demo.dto.PatientDTO;
import com.patitent_induction.demo.entities.Piventity;
import com.patitent_induction.demo.repositories.Validrepository;
import com.patitent_induction.demo.service.PatientUpdate;

@ExtendWith(MockitoExtension.class)
class PatientUpdateTest {

	@Mock
	Validrepository mockValidRepo;
	
	@InjectMocks
	PatientUpdate pUpdate;
	
	@Spy
	ModelMapper modelMapper=new ModelMapper();
	
	@Test
	void getPatientDetailsByIdTest(){
		when(mockValidRepo.findById(1)).thenReturn(Optional.empty());
		assertEquals(Optional.empty(), pUpdate.getPatientDetailsById(1));	
	}	
	
	@Test
	void getPatientDetailsByIdTest2(){
		Optional<Piventity> patient = Optional.ofNullable(new Piventity(1, "rushikesh", "pune", "08-21-2001", "rushi@gmail.com", "7350095218",
				"11111-2222-33", "cipla"));
		when(mockValidRepo.findById(1)).thenReturn(patient);
		assertEquals(patient, pUpdate.getPatientDetailsById(1));	
	}	
	
	@Test
	void updatePatientDetailstest1() {
	
	PatientDTO pdto	= new PatientDTO(1, "rushikesh", "pune", "08-21-2001", "rushi@gmail.com", "7350095218",
				"11111-2222-33", "cipla");
	Piventity piv2 = modelMapper.map(pdto, Piventity.class);
	when(mockValidRepo.save(piv2)).thenReturn(null);
	assertEquals(true, pUpdate.updatePatientDetails(pdto));
	}
	
	@Test
	void updatePatientDetailstest2() {
		
		PatientDTO pdto	= new PatientDTO(1, "rushikesh", "pune", "08-21-2001", "rushi@gmail.com", "7350095218",
					"11111-2222-33", "cipla");
		Piventity piv2 = modelMapper.map(pdto, Piventity.class);
		when(mockValidRepo.save(piv2)).thenReturn(piv2);
		assertEquals(true, pUpdate.updatePatientDetails(pdto));
		}
}
