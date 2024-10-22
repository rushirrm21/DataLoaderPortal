package com.dlp.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dlp.demo.model.LoginCredentials;


@Repository
public interface LoginCredentialsRepository extends JpaRepository<LoginCredentials, String> {
	public LoginCredentials findByUsername(String username);
}