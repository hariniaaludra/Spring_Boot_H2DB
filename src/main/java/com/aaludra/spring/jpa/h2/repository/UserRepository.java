package com.aaludra.spring.jpa.h2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aaludra.spring.jpa.h2.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByCreatedby(String createdby);

	  List<User> findByDisplaynameContaining(String displayname);

}
