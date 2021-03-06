package com.aaludra.spring.jpa.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaludra.spring.jpa.h2.model.Student;
import com.aaludra.spring.jpa.h2.view.Studentoutput;
import com.aaludra.spring.jpa.h2.view.Studentview;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long>{
		List<Student> findBydegree(String degree);
		List<Student> findBycourseContaining(String course);
		
			
		}
	
