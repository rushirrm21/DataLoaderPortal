package com.PatitentInduction.demo.Service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import com.patitent_induction.demo.dto.PatientDTO;
import com.patitent_induction.demo.entities.ProcessedEntity;
import com.patitent_induction.demo.repositories.ProcessedRepository;
import com.patitent_induction.demo.repositories.Validrepository;
import com.patitent_induction.demo.service.ProcessPatient;

@ExtendWith(MockitoExtension.class)
class ProcessPatientTest {

	@Mock
	Validrepository mockValidRepo;
	
	@Mock
	ProcessedRepository mockProcessRepo;
	
	@InjectMocks
	ProcessPatient pProcess;
	
	@Spy
	ModelMapper modelMapper=new ModelMapper();
	
	@Test
	void updatePatientDetailstest1() {
	
	PatientDTO pdto	= new PatientDTO(4, "rushikesh", "pune", "08-21-2001", "rushi@gmail.com", "7350095218",
				"11111-2222-33", "cipla");
	ProcessedEntity piv2 = modelMapper.map(pdto, ProcessedEntity.class);
	when(mockProcessRepo.save(piv2)).thenReturn(null);
	assertEquals(true, pProcess.processPatientData(pdto));
	}
	
	@Test
	void updatePatientDetailstest2() {
		
		PatientDTO pdto	= new PatientDTO(4, "rushikesh", "pune", "08-21-2001", "rushi@gmail.com", "7350095218",
					"11111-2222-33", "cipla");
		ProcessedEntity piv2 = modelMapper.map(pdto, ProcessedEntity.class);
		when(mockProcessRepo.save(piv2)).thenReturn(piv2);
		assertEquals(true, pProcess.processPatientData(pdto));
		}
}
