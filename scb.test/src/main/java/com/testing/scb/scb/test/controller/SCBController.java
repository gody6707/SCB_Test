package com.testing.scb.scb.test.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.testing.scb.scb.test.model.OrdersModel;
import com.testing.scb.scb.test.model.UserInformationModel;
import com.testing.scb.scb.test.repository.OrderRepository;
import com.testing.scb.scb.test.service.UserInformationService;
import com.testing.scb.scb.test.service.UserLoginService;


@RestController
@RequestMapping(value = "/scb", produces = "application/json; charset=utf-8")
public class SCBController {

	protected static final Logger logger = LoggerFactory.getLogger(SCBController.class);

	@Autowired
	UserLoginService userLoginService;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	UserInformationService userInformationService;


	@PostMapping(value = "/login", produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Object login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, @RequestParam(value = "token") Long token) {
		System.out.println(username);
		@SuppressWarnings("unchecked")
		Map<String, Object> response = (Map<String, Object>) userLoginService.login(username, password, token);
		
		return response;
	}
	
	@PostMapping(value = "/saveUserInfo", produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Object saveUserInfo(@RequestBody UserInformationModel userInformationModel) {

		Object response = userInformationService.saveInfo(userInformationModel);

		return response;
	}
	
	@PostMapping(value = "/users", produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Object getUser() {

		Object response = userInformationService.getUsers();

		return response;
	}
	@PostMapping(value = "/deleteUser", produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Object deleteUser() {

		Object response = userInformationService.deleteUsers();

		return response;
	}
	
	@GetMapping(value = "/{users}/{orderId}", produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Object orders(@RequestParam(value = "users") String users , @RequestParam(value = "orderId") Integer orderId) {

		OrdersModel ordersModel = orderRepository.findByUserIdAndOrderId(users, orderId);
		
		return ordersModel;
	}
	
	@PostMapping(value = "/books", produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Object books() throws JSONException {

		try {

			URL url = new URL("https://scb-test-book-publisher.herokuapp.com/books");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				JSONArray jsonArr = new JSONArray(output);

		        for (int i = 0; i < jsonArr.length(); i++)
		        {
		            JSONObject jsonObj = jsonArr.getJSONObject(i);

		            System.out.println(jsonObj);
		        }
		        
				return output;
			}

			
			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }

		return null;
	}
	
	@PostMapping(value = "/recommendation", produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Object recommendation() throws JSONException {

		try {

			URL url = new URL("https://scb-test-book-publisher.herokuapp.com/books/recommendation");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				
				JSONArray jsonArr = new JSONArray(output);
		        for (int i = 0; i < jsonArr.length(); i++)
		        {
		            JSONObject jsonObj = jsonArr.getJSONObject(i);

		            System.out.println(jsonObj);
		        }
				return output;
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }

		return null;
	}

}
