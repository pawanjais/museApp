package com.stackroute.musemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.musemanager.domain.Job;
import com.stackroute.musemanager.exception.JobAlreadyExistsException;
import com.stackroute.musemanager.exception.JobNotFoundException;
import com.stackroute.musemanager.repository.JobRepository;


@Service
public class JobServiceImpl implements JobService{
	
	private final transient JobRepository jobRepo;

	@Autowired
	public JobServiceImpl(JobRepository jobRepo) {
		// TODO Auto-generated constructor stub
		this.jobRepo=jobRepo;
	}

	@Override
	public boolean saveJob(Job job) throws JobAlreadyExistsException {
		System.out.println(job.getId());
		final Job job1=jobRepo.findByIdAndUserId(job.getId(), job.getUserId());
		if(job1 != null){
			throw new JobAlreadyExistsException("Could not save job , job already exists");
		}
		jobRepo.save(job);
		return true;
	}

	@Override
	public Job updateJob(Job updateJob) throws JobNotFoundException {
		final Job job1=jobRepo.findById(updateJob.getrId()).orElse(null);
		if(job1 == null){
			throw new JobNotFoundException("Could not update job , job not found");
		}
		job1.setAlert(updateJob.getAlert());
		jobRepo.save(job1);
		return job1;
	}

	@Override
	public boolean deleteJobById(int id) throws JobNotFoundException {
		final Job job1=jobRepo.findById(id).orElse(null);
		if(job1 == null){
			throw new JobNotFoundException("Could not delete job , job not found");
		}
		jobRepo.delete(job1);
		return true;
	}

	@Override
	public Job getJobById(int id) throws JobNotFoundException {
		final Job job1=jobRepo.findById(id).get();
		if(job1 == null){
			throw new JobNotFoundException("job not found");
		}
		return job1;
	}

	@Override
	public List<Job> getMyJob(String userId) {
		// TODO Auto-generated method stub
		return jobRepo.findByUserId(userId);
	}

	@Override
	public Job findByIdAndUserId(String id, String userId) {
		// TODO Auto-generated method stub
		return jobRepo.findByIdAndUserId(id, userId);
	}

	@Override
	public List<Job> getMyJob() {
		// TODO Auto-generated method stub
		return jobRepo.findAll();
	}

}
