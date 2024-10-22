package com.dlp.demo.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlp.demo.dao.LoginCredentialsRepository;
import com.dlp.demo.model.LoginCredentials;

@Service
public class LoginServiceImpl implements LoginService {

	Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private LoginCredentialsRepository loginCredentialsRepository;

	@Override
	public Optional<LoginCredentials> findByUsername(String userName) {
		logger.info("Checking credentials are present in DB by findByUsername()");
		return loginCredentialsRepository.findById(userName);
	}

	@Override
	public void changePass(LoginCredentials loginCred) {
		logger.info("Password to be changed");
		loginCredentialsRepository.save(loginCred);
		logger.info("Password changed by changePass()");
	}
}
