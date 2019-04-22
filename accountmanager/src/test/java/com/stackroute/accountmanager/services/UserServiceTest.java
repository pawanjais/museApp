package com.stackroute.accountmanager.services;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.accountmanager.domain.User;
import com.stackroute.accountmanager.exception.UserAlreadyExistsException;
import com.stackroute.accountmanager.exception.UserNotFoundException;
import com.stackroute.accountmanager.repository.UserRepository;
import com.stackroute.accountmanager.service.UserServiceImpl;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class UserServiceTest {

	public UserServiceTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Mock
	private UserRepository userRepo;
	
	private User user;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	
	Optional<User> options;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User("Pawan","Jaiswal","pawan@com","123456789");
		options=Optional.of(user);
	}
	
	@Test
	public void testSaveUserSuccess() throws UserAlreadyExistsException{
		when(userRepo.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
		assertEquals("can not regsiter user",true,flag);
		verify(userRepo, times(1)).save(user);
		
	}
	@Test(expected=UserAlreadyExistsException.class)
	public void testSaveUserFailure() throws UserAlreadyExistsException{
		when(userRepo.findById(user.getUserId())).thenReturn(options);
		when(userRepo.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
		
		
	}
	
	@Test
	public void testValidDataSuccess() throws UserNotFoundException{
		when(userRepo.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User userResult = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals("Pawan",userResult.getUserId());
		verify(userRepo,times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
		
	}
	
	@Test(expected=UserNotFoundException.class)
	public void testValidateFailure() throws UserNotFoundException{
		when(userRepo.findById("abc")).thenReturn(null);
		userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}


}
