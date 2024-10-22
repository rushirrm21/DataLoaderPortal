package com.PatitentInduction.demo.Service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import com.patitent_induction.demo.entities.Pinventity;
import com.patitent_induction.demo.entities.Piventity;
import com.patitent_induction.demo.repositories.InvalidRepository;
import com.patitent_induction.demo.repositories.Validrepository;
import com.patitent_induction.demo.service.Patientinductionservice;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

	@InjectMocks
	public Patientinductionservice mockService;

	@Mock
	public InvalidRepository invalidRepoMock;

	@Mock 
	public Validrepository validRepoMock;
	
	@Spy
	ModelMapper modelMapper=new ModelMapper();
	
	@SuppressWarnings("deprecation")
	@Test
	void saveTest1() throws IOException {
		FileInputStream excelSheetInput = new FileInputStream("C:/PatientInduction2.xlsx");
		MultipartFile multipartFile = new MockMultipartFile("data", excelSheetInput);
		boolean actualResult = mockService.save(multipartFile);
		Boolean act = new Boolean(actualResult);
		Boolean exp = new Boolean(false);
		assertEquals(act, exp);
	}

	@SuppressWarnings("deprecation")
	@Test
	void saveTest2() throws IOException {

		FileInputStream excelSheetInput = new FileInputStream("C:/PatientInduction.xlsx");
		MultipartFile multipartFile = new MockMultipartFile("data", excelSheetInput);
		Pinventity pinv = new Pinventity(1, "rushikesh", "pune", "08-21-2001", "rushi@gmail.com", "735009521",
				"11111-2222-33", "cipla");
		when(invalidRepoMock.save(pinv)).thenReturn(pinv);
		boolean actualResult = mockService.save(multipartFile);
		Boolean act = new Boolean(actualResult);
		Boolean exp = new Boolean(true);
		assertEquals(act, exp);
	}

	@SuppressWarnings("deprecation")
	@Test
	void saveTest3() throws IOException {

		FileInputStream excelSheetInput = new FileInputStream("C:/PatientInduction.xlsx");
		MultipartFile multipartFile = new MockMultipartFile("data", excelSheetInput);
		Piventity pinv = new Piventity(1, "rushikesh", "pune", "08-21-2001", "rushi@gmail.com", "7350095218",
				"11111-2222-33", "cipla");
		when(validRepoMock.save(pinv)).thenReturn(null);
		boolean actualResult = mockService.save(multipartFile);
		Boolean act = new Boolean(actualResult);
		Boolean exp = new Boolean(true);
		assertEquals(act, exp);
	}
	
	@Test
	void convertTest1() throws IOException {
		FileInputStream excelSheetInput = new FileInputStream("C:/PatientInduction.xlsx");
		List<Pinventity> actualResult = mockService.convertExcelToListOfPatients(excelSheetInput);
		Pinventity addExpectedresult = new Pinventity(null, "rushikesh", "pune", "08-21-2001", "rushi@gmail.com",
				"7350095218", "11111-2222-33", "cipla");
		Pinventity addExpectedresult1 = new Pinventity(null, "abhijit", "Kolkata", "09-30-2022", "rushi@gmail.com",
				"3350095218", "11111-2222-33", "cipla");
		Pinventity addExpectedresult2 = new Pinventity(null, "Lokesh", "Goa", null, null, null, null, null);
		Pinventity addExpectedresult3 = new Pinventity(null, "charles", "pune", "08-21-2023", "rushi@gmail.com",
				"7350095218", "1111-2222-33", "cipla");
		Pinventity addExpectedresult4 = new Pinventity(null, null, null, null, null, null, null, null);
		List<Pinventity> expectedResult = new ArrayList<>();
		expectedResult.add(addExpectedresult);
		expectedResult.add(addExpectedresult1);
		expectedResult.add(addExpectedresult2);
		expectedResult.add(addExpectedresult3);
		expectedResult.add(addExpectedresult4);
		assertEquals(actualResult, expectedResult);

	}

	@Test
	void convertTest2() throws IOException {
		FileInputStream excelSheetInput = new FileInputStream("C:/PatientInduction2.xlsx");
		List<Pinventity> actualResult = mockService.convertExcelToListOfPatients(excelSheetInput);
		List<Pinventity> expectedResult = new ArrayList<>();
		assertEquals(actualResult, expectedResult);

	}
}