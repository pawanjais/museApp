package com.stackroute.accountmanager.repositories;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.accountmanager.domain.User;
import com.stackroute.accountmanager.repository.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserRepositoryTest {

	public UserRepositoryTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private UserRepository userRepository;
	
	
	private User user;
	
	@Before
	public void setUp() throws Exception{
		user = new User("Pawan","Jaiswal","pawan@com","123456789");
	}
	
	@Test
	public void testRegisterSuccess() {
		userRepository.save(user);
		Optional<User> option =userRepository.findById(user.getUserId());
		assertThat(option.equals(user));
		}


}
