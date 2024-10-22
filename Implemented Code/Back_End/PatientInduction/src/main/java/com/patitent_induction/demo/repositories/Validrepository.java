package com.patitent_induction.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.patitent_induction.demo.entities.Piventity;

public interface Validrepository extends JpaRepository<Piventity, Integer> {
}
