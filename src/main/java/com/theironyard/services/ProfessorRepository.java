package com.theironyard.services;

import com.theironyard.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jeff on 8/8/16.
 */
public interface ProfessorRepository extends JpaRepository<Professor, Integer>{
}
