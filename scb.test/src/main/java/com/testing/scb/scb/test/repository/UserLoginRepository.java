package com.testing.scb.scb.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.testing.scb.scb.test.model.UserLoginModel;


@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginModel, String> {
	
	@Query(value = "Select * from user_login Where username = ?1 AND password = ?2", nativeQuery = true)
	public UserLoginModel login(String username , String password);
	
	@Query(value = "Select * from user_login Where username = ?1", nativeQuery = true)
	public UserLoginModel checkAccout(String username);

}
