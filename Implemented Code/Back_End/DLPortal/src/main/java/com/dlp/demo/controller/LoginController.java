package com.dlp.demo.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dlp.demo.dto.LoginCredentialsDTO;
import com.dlp.demo.model.LoginCredentials;
import com.dlp.demo.services.LoginService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	@Autowired
	public ModelMapper modelMapper;

	@PostMapping("/login")
	public String postLogin(@RequestBody LoginCredentialsDTO loginCred) {
		
		logger.info("Invoked postLogin()");
		
		LoginCredentials loginCredd = this.modelMapper.map(loginCred, LoginCredentials.class);
		logger.info("Model Mapping completed");
		Optional<LoginCredentials> credentialsFromDB = loginService.findByUsername(loginCredd.getUsername());
		if (credentialsFromDB.isPresent()) {
			logger.info("Credentials are present in DB");
			if (loginCredd.getPassword().equals(credentialsFromDB.get().getPassword())) {
				logger.info("Logged in Successfully");
				return "Logged in Successfully";
			} else {
				logger.info("Incorrect Password");
				return "Incorrect Password";
			}
		}
		logger.info("Credentials are not present in DB");
		return "Incorrect Username";
	}

	@PutMapping("/forgotpwd")
	public String changePassword(@RequestBody LoginCredentialsDTO loginCred) {
		logger.info("changePassword() invoked");
		LoginCredentials loginCredd = this.modelMapper.map(loginCred, LoginCredentials.class);
		Optional<LoginCredentials> credentialsFromDB = loginService.findByUsername(loginCredd.getUsername());
		if (credentialsFromDB.isPresent()) {
			loginService.changePass(loginCredd);
			logger.info("Changed password");
			return "Password Changed";
		}
		logger.info("Username is not present in DB");
		return "Incorrect Username";
	}
}