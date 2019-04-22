package com.stackroute.musemanager.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.stackroute.musemanager.controller.JobController;
import com.stackroute.musemanager.domain.Job;
import com.stackroute.musemanager.service.JobService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(JobController.class)

public class JobControllerTest {

	public JobControllerTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private transient MockMvc mvc;
	
	@MockBean
	private transient JobService service;
	
	private transient Job job;
	
	static List<Job> jobs;
	
	@Before
	public void setup() {
		jobs=new ArrayList<>();
		job=new Job(1,"11","pawan","30/03/2019","important","Pawan");
		jobs.add(job);
		job=new Job(2,"21","THOMAS","30/03/2019","important","101");
		jobs.add(job);
		job=new Job(3,"11","gagan","30/03/2019","important","102");
		jobs.add(job);
		
	}

	@Test
	public void testSavejobSuccess() throws Exception{
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQYXdhbiIsImlhdCI6MTU1MzYwMjQ5MX0.yK7azElbcy1GHJfSCH11mA8nHVdBSmHnLQjjSD_w9Ys";
		when(service.saveJob(job)).thenReturn(true);
		mvc.perform(post("/api/jobservice/job").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(job))).andExpect(status().isCreated());
		verify(service,times(1)).saveJob(Mockito.any(Job.class));
		verifyNoMoreInteractions(service);
		
	}
	
	@Test
	public void testUpdatejobSuccess() throws Exception{
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDEiLCJpYXQiOjE1NTM0OTIwMDF9.WZgy61lglK33IdtI_7pf0va65lkzJTRzt0s9IzY7VHA";
		job.setAlert("not so good");
		when(service.updateJob(job)).thenReturn(jobs.get(0));
		mvc.perform(put("/api/jobservice/job/{id}",1).header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(job)))
		.andExpect(status().isOk());
		verify(service,times(1)).updateJob(Mockito.any(Job.class));
		verifyNoMoreInteractions(service);
	}
	
/*	@Test
	public void testDeletejobById()throws Exception{
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDEiLCJpYXQiOjE1NTM0OTIwMDF9.WZgy61lglK33IdtI_7pf0va65lkzJTRzt0s9IzY7VHA";
		when(service.deletejobById(1)).thenReturn(true);
		mvc.perform(delete("/api/jobservice/job/{id}/{userId}","1234","101").header("authorization", "Bearer " + token)).andExpect(status().isOk());
		verify(service,times(1)).deletejobById(1);
		verifyNoMoreInteractions(service);
	}
*/

	
	@Test
	public void testFetchjobById()throws Exception{
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQYXdhbiIsImlhdCI6MTU1MzYwMjQ5MX0.yK7azElbcy1GHJfSCH11mA8nHVdBSmHnLQjjSD_w9Ys";
		when(service.getJobById(1)).thenReturn(jobs.get(0));
		mvc.perform(get("/api/jobservice/job/{id}",1).header("authorization", "Bearer " + token)).andExpect(status().isOk());
		verify(service,times(1)).getJobById(1);
		verifyNoMoreInteractions(service);
	}
	
	@Test
	public void testGetMyjobs() throws Exception{
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJQYXdhbiIsImlhdCI6MTU1MzYwMjQ5MX0.yK7azElbcy1GHJfSCH11mA8nHVdBSmHnLQjjSD_w9Ys";
		when(service.getMyJob("Pawan")).thenReturn(jobs);
		mvc.perform(get("/api/jobservice/job")
				.header("authorization","Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andDo(print());
		verify(service,times(1)).getMyJob("Pawan");
		verifyNoMoreInteractions(service);
	}

	
/*	@Test
	public void testFetchAlljobs() throws Exception {
		when(service.getAlljobs()).thenReturn(null);
		mvc.perform(get("/api/job")).andExpect(status().isOk());
		verify(service,times(1)).getAlljobs();
		verifyNoMoreInteractions(service);
	}*/
	private static String jsonToString(final Object obj) throws JsonProcessingException{
		String result;
		try {
			final ObjectMapper mapper=new ObjectMapper();
			final String jsonContent=mapper.writeValueAsString(obj);
			result =jsonContent;
					
		}catch(JsonProcessingException e) {
			result="json processing error";
		}
		return result;
	}



}
