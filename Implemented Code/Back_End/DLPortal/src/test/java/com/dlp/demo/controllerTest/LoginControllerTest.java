package com.dlp.demo.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.dlp.demo.controller.LoginController;
import com.dlp.demo.dto.LoginCredentialsDTO;
import com.dlp.demo.model.LoginCredentials;
import com.dlp.demo.services.LoginServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class LoginControllerTest {
	@Autowired
	LoginController logCnt1;
	@InjectMocks
	LoginController logCntMock;
	@Mock
	LoginServiceImpl logService;
	@Spy
	ModelMapper modelMapper=new ModelMapper();
	@Autowired
	public ModelMapper modelMapperInTest;

	@Test
	void loginTest1() {
		LoginCredentialsDTO logCred1 = new LoginCredentialsDTO("rushi@gmail.com", "QWE@21rrm");
		assertEquals("Logged in Successfully", logCnt1.postLogin(logCred1));
	}

	@Test
	void loginTest2() {
		LoginCredentialsDTO logCred1 = new LoginCredentialsDTO("rushi@gmail.com", "QQE@21rrm");
		assertEquals("Incorrect Password", logCnt1.postLogin(logCred1));
	}

	@Test
	void loginTest3() {
		LoginCredentialsDTO logCred1 = new LoginCredentialsDTO("rushik@gmail.com", "QQE@21rrm");
		assertEquals("Incorrect Username", logCnt1.postLogin(logCred1));
	}

	@Test
	void loginTest4() {
		LoginCredentialsDTO logCred1 = new LoginCredentialsDTO("rushik@gmail.com", "QWE@21rrm");
		Optional<LoginCredentials> logCred2 = Optional.of(new LoginCredentials("rushi@gmail.com", "QWE@21rrm"));
		when(logService.findByUsername("rushik@gmail.com")).thenReturn(logCred2);
		assertEquals("Logged in Successfully", logCntMock.postLogin(logCred1));
	}

	@Test
	void loginTest5() {
		LoginCredentialsDTO logCred1 = new LoginCredentialsDTO("rushi@gmail.com", "QWE@21rrm");
		Optional<LoginCredentials> logCred2 = Optional.of(new LoginCredentials("rushi@gmail.com", "QWE@21rrmzx"));
		when(logService.findByUsername("rushi@gmail.com")).thenReturn(logCred2);
		assertEquals("Incorrect Password", logCntMock.postLogin(logCred1));
	}

	@Test
	void loginTest6() {
		LoginCredentialsDTO logCred1 = new LoginCredentialsDTO("rushi@gmail.co", "QWE@21rrm");
		Optional<LoginCredentials> logCred2 = Optional.of(new LoginCredentials("rushi@gmail.com", "QWE@21rrm"));
		when(logService.findByUsername("rushh@gmail.com")).thenReturn(logCred2);
		assertEquals("Incorrect Username", logCntMock.postLogin(logCred1));
	}

	@Test
	void changePassword1() {
		LoginCredentialsDTO logCred1 = new LoginCredentialsDTO("rushi@gmail.com", "QWE@21rrmx");
		LoginCredentials loginCredd = this.modelMapperInTest.map(logCred1, LoginCredentials.class);
		Optional<LoginCredentials> logCred2 = Optional.of(new LoginCredentials("rushi@gmail.com", "QWE@21rrm"));
		when(logService.findByUsername("rushi@gmail.com")).thenReturn(logCred2);
		logService.changePass(loginCredd);
		assertEquals("Password Changed", logCntMock.changePassword(logCred1));
	}

	@Test
	void changePassword2() {
		LoginCredentialsDTO logCred1 = new LoginCredentialsDTO("rushi@gmail.com", "QWE@21rrm");
		LoginCredentials loginCredd = this.modelMapperInTest.map(logCred1, LoginCredentials.class);
		Optional<LoginCredentials> logCred2 = Optional.of(new LoginCredentials());
		when(logService.findByUsername("rushh@gmail.com")).thenReturn(logCred2);
		logService.changePass(loginCredd);
		assertEquals("Incorrect Username", logCntMock.changePassword(logCred1));
	}
}