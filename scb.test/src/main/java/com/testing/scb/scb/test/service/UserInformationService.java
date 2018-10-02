package com.testing.scb.scb.test.service;

import com.testing.scb.scb.test.model.UserInformationModel;

public interface UserInformationService {
	
	Object saveInfo(UserInformationModel userInformationModel);
	Object getUsers();
	Object deleteUsers();

}
