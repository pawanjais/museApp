package com.stackroute.musemanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.musemanager.domain.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {

	
	List<Job> findByUserId(String userId);
	
	Optional<Job> findById(String id);
	
	Job findByIdAndUserId(String id,String userId);

}
