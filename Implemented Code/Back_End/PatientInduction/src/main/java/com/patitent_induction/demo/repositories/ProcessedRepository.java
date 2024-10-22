package com.patitent_induction.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.patitent_induction.demo.entities.ProcessedEntity;

public interface ProcessedRepository extends JpaRepository<ProcessedEntity, Integer> {

}
