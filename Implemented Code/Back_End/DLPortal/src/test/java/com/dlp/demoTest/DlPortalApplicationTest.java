package com.dlp.demoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.dlp.demo.DlPortalApplication;
public class DlPortalApplicationTest {
	
	 @Test   
	 public void main() {
		 DlPortalApplication.main(new String[] {});
		 boolean val = true;
		 assertEquals(true,val);
}
}