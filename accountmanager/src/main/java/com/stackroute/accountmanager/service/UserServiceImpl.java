package com.stackroute.accountmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.accountmanager.domain.User;
import com.stackroute.accountmanager.exception.UserAlreadyExistsException;
import com.stackroute.accountmanager.exception.UserNotFoundException;
import com.stackroute.accountmanager.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo=userRepo;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub
		final Optional<User> users=userRepo.findById(user.getUserId());
		if(users.isPresent()) {
			throw new UserAlreadyExistsException("user with id already exsts");
		}
		userRepo.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = userRepo.findByUserIdAndPassword(userId, password);
		if(user==null)
			throw new UserNotFoundException("user not found");
		
		return user;
	}

}
