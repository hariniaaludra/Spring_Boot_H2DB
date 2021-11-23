package com.aaludra.spring.jpa.h2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aaludra.spring.jpa.h2.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
<<<<<<< HEAD
  List<Tutorial> findByPublished(boolean published);

  List<Tutorial> findByTitleContaining(String title);
=======
 List<Tutorial> findByPublished(boolean published);

 List<Tutorial> findByTitleContaining(String title);
>>>>>>> 5f353c228c9805fc681195161fc4eb70ec699960
}
