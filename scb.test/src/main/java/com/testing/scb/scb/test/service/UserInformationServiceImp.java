package com.testing.scb.scb.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.scb.scb.test.model.UserInformationModel;
import com.testing.scb.scb.test.repository.UserInformationRepository;

@Service
public class UserInformationServiceImp implements UserInformationService {
	
	@Autowired
	private UserInformationRepository userInformationRepository;
	
	private UserInformationModel userInformationModel;

	@Override
	public Object saveInfo(UserInformationModel userInformationModelParam) {
		
		userInformationModel = userInformationRepository.findByUserId(userInformationModelParam.getUserId());
		
		if(userInformationModel == null) {
			
			userInformationRepository.save(userInformationModelParam);
			
		}else {
			userInformationModel.setPhone(userInformationModelParam.getPhone());
			userInformationModel.setAddress(userInformationModelParam.getAddress());
			userInformationModel.setCity(userInformationModelParam.getCity());
			userInformationModel.setCountry(userInformationModelParam.getCountry());
			userInformationModel.setDateBirthday(userInformationModelParam.getDateBirthday());
			userInformationModel.setZip(userInformationModelParam.getZip());
			userInformationModel.setState(userInformationModelParam.getState());
			
			userInformationRepository.save(userInformationModel);
		}
		
		return "Data saved successfully!";
	}

	@Override
	public Object getUsers() {
		// TODO Auto-generated method stub
		return userInformationRepository.findAll();
	}

	@Override
	public Object deleteUsers() {
		userInformationRepository.deleteAll();
		return "Delete Success"; 
	}
}
