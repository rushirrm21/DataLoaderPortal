package com.patitent_induction.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patitent_induction.demo.entities.Pinventity;

public interface InvalidRepository extends JpaRepository<Pinventity, Integer> {

}
