package com.stackroute.accountmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.accountmanager.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByUserIdAndPassword(String userId,String password);

}
