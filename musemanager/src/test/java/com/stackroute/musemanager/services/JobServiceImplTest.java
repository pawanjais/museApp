package com.stackroute.musemanager.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.musemanager.domain.Job;
import com.stackroute.musemanager.exception.JobAlreadyExistsException;
import com.stackroute.musemanager.exception.JobNotFoundException;
import com.stackroute.musemanager.repository.JobRepository;
import com.stackroute.musemanager.service.JobServiceImpl;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;


public class JobServiceImplTest {

	public JobServiceImplTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Mock
	private transient JobRepository jobRepo;
	
	private transient Job job;
	
	@InjectMocks
	private transient JobServiceImpl jobServiceImpl;

	
	transient Optional<Job> options;
	
	@Before
	public void setupMock(){
		MockitoAnnotations.initMocks(this);
		job = new Job(1,"11","pawan","30/03/2019","important","101");
		options=Optional.of(job);
	}
	
	@Test
	public void testMockCreation(){
		assertNotNull("jpaRepository creation fails : use @injectMocks on jobServiceImpl",job);
	}
	
	
	@Test
	public void testSaveJobSuccess() throws JobAlreadyExistsException {
		when(jobRepo.save(job)).thenReturn(job);
		final boolean flag = jobServiceImpl.saveJob(job);
		assertTrue("Saving job failed",flag);
		verify(jobRepo,times(1)).save(job);
		verify(jobRepo,times(1)).findByIdAndUserId(job.getId(), job.getUserId());

	}
	
	@Test(expected=JobAlreadyExistsException.class)
	public void testSavejobFailure() throws JobAlreadyExistsException {
		when(jobRepo.findByIdAndUserId("11", "101")).thenReturn(job);
		when(jobRepo.save(job)).thenReturn(job);
		final boolean flag = jobServiceImpl.saveJob(job);
		//System.out.println(flag);
		assertFalse("Saving job failed",flag);
		verify(jobRepo,times(1)).findByIdAndUserId(job.getId(), job.getUserId());
		


	}
	
	@Test
	public void testUpdatejob() throws JobNotFoundException {
		when(jobRepo.findById(1)).thenReturn(options);
		when(jobRepo.save(job)).thenReturn(job);
		job.setAlert("not so good job");
		final Job job1 = jobServiceImpl.updateJob(job);
		assertEquals("updating job failed","not so good job",job1.getAlert());
		verify(jobRepo,times(1)).save(job);
		verify(jobRepo,times(1)).findById(job.getrId());

	}
	
	@Test
	public void testDeletejobById() throws JobNotFoundException{
		when(jobRepo.findById(1)).thenReturn(options);
		doNothing().when(jobRepo).delete(job);
		final boolean flag = jobServiceImpl.deleteJobById(1);
		assertTrue("deleting job failed",flag);
		verify(jobRepo,times(1)).delete(job);
		verify(jobRepo,times(1)).findById(job.getrId());
	}
	
	@Test
	public void testGetjobById() throws JobNotFoundException{
		when(jobRepo.findById(1)).thenReturn(options);
		final Job job1 = jobServiceImpl.getJobById(1);
		assertEquals("fetching job by id failed",job1,job);
		verify(jobRepo,times(1)).findById(job.getrId());
	}
	
	@Test
	public void testGetAlljobs(){
		final List<Job> jobList= new ArrayList<>(1);
		when(jobRepo.findAll()).thenReturn(jobList);
		final List<Job> job1=jobServiceImpl.getMyJob("101");
		assertEquals(jobList,job1);
		verify(jobRepo,times(1)).findByUserId("101");
	}

}
