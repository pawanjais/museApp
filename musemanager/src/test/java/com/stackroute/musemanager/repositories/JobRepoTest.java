package com.stackroute.musemanager.repositories;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.musemanager.domain.Job;
import com.stackroute.musemanager.repository.JobRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class JobRepoTest {

	public JobRepoTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private transient JobRepository repo;
	
	
	public void setRepo(final JobRepository repo){
		this.repo=repo;
	}
	
	@Test
	public void testSaveJob() throws Exception {
		repo.save(new Job(1,"11","pawan","30/03/2019","important","101"));
		final Job getJob = repo.getOne(1);
		assertThat(getJob.getrId()).isEqualTo(1);

	}
	
	@Test
	public void testUpdateJob() throws Exception {
		Job savedJob=repo.save(new Job(1,"11","pawan","30/03/2019","important","101"));
		final Job jobs = repo.getOne(savedJob.getrId());
		assertEquals(jobs.getName(),"pawan");
		jobs.setAlert("Important");
		repo.save(jobs);
		final Job tempJob = repo.getOne(savedJob.getrId());
		assertEquals("Important",tempJob.getAlert());
		
	}
	
	@Test
	public void testDeleteJob() throws Exception {
		Job savedJob=repo.save(new Job(1,"11","pawan","30/03/2019","important","101"));
		final Job jobs = repo.getOne(savedJob.getrId());
		assertEquals(jobs.getName(),"pawan");
		repo.delete(jobs);
		assertEquals(Optional.empty(),repo.findById(savedJob.getrId()));
		
	}
	
	@Test
	public void testGetMovie() throws Exception {
		Job savedJob=repo.save(new Job(1,"11","pawan","30/03/2019","important","101"));
		final Job jobs = repo.getOne(savedJob.getrId());
		assertEquals(jobs.getName(),"pawan");
		
	}
	
	@Test
	public void testGetAllJob() throws Exception {
		repo.save(new Job(1,"11","pawan","30/03/2019","important","101"));
		repo.save(new Job(2,"11","thomas","30/03/2019","important","102"));
		final List<Job> jobs = repo.findAll();
		assertEquals(jobs.get(0).getName(),"pawan");
		
	}
	
	@After
    public void tearDown() {
        repo.deleteAllInBatch();;
    }

}
