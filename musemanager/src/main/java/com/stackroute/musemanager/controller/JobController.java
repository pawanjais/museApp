package com.stackroute.musemanager.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.musemanager.domain.Job;
import com.stackroute.musemanager.exception.JobAlreadyExistsException;
import com.stackroute.musemanager.exception.JobNotFoundException;
import com.stackroute.musemanager.service.JobService;
import io.jsonwebtoken.Jwts;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/jobservice")
public class JobController {
	
	private JobService jobService;
	
	public JobController(JobService jobService) {
		this.jobService=jobService;
	}
	
	@PostMapping("/job")
	public ResponseEntity<?> saveNewMovie(@RequestBody final Job job,HttpServletRequest request,HttpServletResponse response){
		ResponseEntity<?> responseEntity;
		final String authHeader = request.getHeader("authorization");
    	final String token = authHeader.substring(7);
    	String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
    	System.out.println("userId:"+userId);
		try {
			job.setUserId(userId);
			jobService.saveJob(job);
			responseEntity = new ResponseEntity<Job>(job,HttpStatus.CREATED);
		} catch (JobAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("{\"message\":\"" + e.getMessage() +"\"}",HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	@PutMapping(path ="/job/{id}")
	public ResponseEntity<?> updateJob(@PathVariable("id") final Integer id,@RequestBody final Job job){
		ResponseEntity<?> responseEntity;
		
		try {
			final Job fetchJob = jobService.updateJob(job);
			responseEntity = new ResponseEntity<Job>(fetchJob,HttpStatus.OK);
		} catch (JobNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{\"message\":\"" + e.getMessage() +"\"}",HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	@DeleteMapping(path ="/job/{id}/{userId}")
	public ResponseEntity<?> deleteJob(@PathVariable("id") final String id,@PathVariable("userId") final String userId){
		final Job jobs=jobService.findByIdAndUserId(id, userId);
		ResponseEntity<?> responseEntity;
		try {
			jobService.deleteJobById(jobs.getrId());
		} catch (JobNotFoundException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		responseEntity = new ResponseEntity<String>("movie deleted successfully",HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(path ="/job/{id}")
	public ResponseEntity<?> fetchMovieById(@PathVariable("id") final int id){
		ResponseEntity<?> responseEntity;
		Job jobs = null;
		try {
			jobs = jobService.getJobById(id);
		} catch (JobNotFoundException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		responseEntity = new ResponseEntity<Job>(jobs,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/job")
	public @ResponseBody ResponseEntity<List<Job>> fetchMyMovies(final ServletRequest req,final ServletResponse res){
		final HttpServletRequest request = (HttpServletRequest) req;
	    final String authHeader =  request.getHeader("authorization");
	    final String token = authHeader.substring(7);
	    String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
	    System.out.println("userId"+userId);
	    return new ResponseEntity<List<Job>>(jobService.getMyJob(userId),HttpStatus.OK);
	}

}
