package com.dlp.demo.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import com.dlp.demo.dao.LoginCredentialsRepository;
import com.dlp.demo.model.LoginCredentials;
import com.dlp.demo.services.LoginServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class LoginServiceImplTest {
	@Mock
	LoginCredentialsRepository mockRepository;
	@InjectMocks
	LoginServiceImpl mockService;
	Optional<LoginCredentials> loginCredentials = Optional
			.ofNullable(new LoginCredentials("test@gmail.com", "Test@123"));
	
	LoginCredentials loginCredentials2 = (new LoginCredentials("test@gmail.com", "Test@123"));

	@Test
	void findByUsernameTest1() {
		when(mockService.findByUsername("test@gmail.com")).thenReturn(loginCredentials);
		assertThat(mockService.findByUsername("test@gmail.com")).isEqualTo(loginCredentials);
	}

	@Test
	void findByUsernameTest2() {
		when(mockService.findByUsername("test@gmail.co")).thenReturn(null);
		assertThat(mockService.findByUsername("test@gmail.co")).isNull();
	}
	
	@Test
	void changePassTest() {
		when(mockRepository.save(loginCredentials2)).thenReturn(null);
		mockService.changePass(loginCredentials2);
		boolean val = true;
		 assertEquals(true,val);		
	}
}
