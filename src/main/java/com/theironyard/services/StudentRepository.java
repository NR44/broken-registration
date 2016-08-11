package com.theironyard.services;

import com.theironyard.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jeff on 8/8/16.
 */
public interface StudentRepository extends JpaRepository<Student, String>{
}
