package com.dlp.demo.services;

import java.util.Optional;

import com.dlp.demo.model.LoginCredentials;

public interface LoginService {
	public Optional<LoginCredentials> findByUsername(String userName);

	public void changePass(LoginCredentials loginCred);
}