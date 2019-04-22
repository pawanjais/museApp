package com.stackroute.musemanager.service;

import java.util.List;

import com.stackroute.musemanager.domain.Job;
import com.stackroute.musemanager.exception.JobAlreadyExistsException;
import com.stackroute.musemanager.exception.JobNotFoundException;


public interface JobService {

	boolean saveJob(Job job) throws JobAlreadyExistsException;
	
	Job updateJob(Job job) throws JobNotFoundException;
	
	boolean deleteJobById(int id) throws JobNotFoundException;

	Job getJobById(int id) throws JobNotFoundException;
	
	List<Job> getMyJob(String userId);
	
	List<Job> getMyJob();
	
	Job findByIdAndUserId(String id,String userId);
}
