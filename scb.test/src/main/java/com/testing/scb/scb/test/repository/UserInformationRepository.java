package com.testing.scb.scb.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.testing.scb.scb.test.model.UserInformationModel;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformationModel, Object> {

	@Query(value = "Select * from user_information Where user_id = ?1", nativeQuery = true)
	public UserInformationModel findByUserId(Integer userId);
}
