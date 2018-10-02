package com.testing.scb.scb.test.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.scb.scb.test.model.UserLoginModel;
import com.testing.scb.scb.test.repository.UserLoginRepository;

@Service
public class UserLoginServiceImp implements UserLoginService {

	protected static final Logger logger = LoggerFactory.getLogger(UserLoginServiceImp.class);

	@Autowired
	private UserLoginRepository userLoginRepository;

	private UserLoginModel usererModel;

	@Override
	public Object login(String username, String password, Long token) {

		usererModel = userLoginRepository.checkAccout(username);
		if(usererModel != null) {
			usererModel = userLoginRepository.login(username, (password));
			if(usererModel != null) {
					return "Token Mismatch!";
				
			}else {
				return "Password Mismatch!";
			}
		
		}else {
			return ("Don't have your account , Please Register!");
		}

	}

}
