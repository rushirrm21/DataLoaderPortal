package com.PatitentInduction.demo.entity;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.patitent_induction.demo.entities.Piventity;

public class PiventityTest {

	
	Piventity piv = new Piventity(1,"RUSHIKESH","Pune","08-21-2001","rushi@gmail.com","7350095218","11111-2222-33","Cipla");
	
	@Test
	public void toStringTest() {
		String str = piv.toString();
		assertEquals(str, piv.toString());
	}
	
	@Test
	public void hashCodeTest() {
		Piventity piv2 = piv;
		assertEquals(piv2.hashCode(), piv.hashCode());
	}

	@Test
	public void setMethodTest() {
		Piventity piv2 = new Piventity();
		piv2.setPatientId(1);
		piv2.setPatientName("RUSHIKESH");
		piv2.setPatientAddress("Pune");
		piv2.setPatientDateofBirth("08-21-2001");
		piv2.setPatientEmail("rushi@gmail.com");
		piv2.setPatientContactNumber("7350095218");
		piv2.setPatientDrugId("11111-2222-33");
		piv2.setPatientDrugName("Cipla");
		Piventity pivCheck = new Piventity(1,"RUSHIKESH","Pune","08-21-2001","rushi@gmail.com","7350095218","11111-2222-33","Cipla");
		assertEquals(piv2, pivCheck);
	}
	
	@Test
	public void getMethodTest1() {
		assertEquals("RUSHIKESH",piv.getPatientName());
	}
	@Test
	public void getMethodTest2() {
		assertEquals("Pune",piv.getPatientAddress());
	}
	@Test
	public void getMethodTest3() {
		assertEquals("08-21-2001",piv.getPatientDateofBirth());
	}
	@Test
	public void getMethodTest4() {
		assertEquals("rushi@gmail.com",piv.getPatientEmail());
	}
	@Test
	public void getMethodTest5() {
		assertEquals("7350095218",piv.getPatientContactNumber());
	}
	@Test
	public void getMethodTest6() {
		assertEquals("11111-2222-33",piv.getPatientDrugId());
	}
	@Test
	public void getMethodTest7() {
		assertEquals("Cipla",piv.getPatientDrugName());
	}
	
	@Test
	public void getMethodTest8() {
		Integer id = 1;
		assertEquals(id,piv.getPatientId());
	}
}
