package com.dlp.demo.dtoTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.dlp.demo.dto.LoginCredentialsDTO;

public class LoginCredentialsDTOTest {
	@Test
	public void setMethodTest() {
		LoginCredentialsDTO logCred = new LoginCredentialsDTO();
		logCred.setUsername("rushi@gmail.com");
		logCred.setPassword("QWE@21rrm");
		LoginCredentialsDTO logCred2 = logCred;
		assertEquals(logCred, logCred2);
	}
}
