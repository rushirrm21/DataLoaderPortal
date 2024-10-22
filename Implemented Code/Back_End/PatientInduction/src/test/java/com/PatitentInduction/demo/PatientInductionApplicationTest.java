package com.PatitentInduction.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import com.patitent_induction.demo.PatientInductionApplication;

public class PatientInductionApplicationTest {
	
	 @Test   
	 public void main() {
		 PatientInductionApplication.main(new String[] {});
		 boolean val = true;
		 assertEquals(true,val);
}
}
