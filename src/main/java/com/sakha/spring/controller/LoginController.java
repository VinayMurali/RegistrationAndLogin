package com.sakha.spring.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sakha.spring.dto.Userdto;
import com.sakha.spring.model.Userdetails;
import com.sakha.spring.service.Userservice;


@RestController
public class LoginController {

	@Autowired
	public Userservice userService;

	private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> login(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> resopnseObj = new HashMap<String, String>();
		String userName = request.getParameter("uname");
		String password = request.getParameter("password");
		String result = userService.validateUser(userName, password);
		resopnseObj.put("message", result);
		return new ResponseEntity<Map<String, String>>(resopnseObj, HttpStatus.OK);
	}

	// @RequestMapping(value = "/plogin", method = RequestMethod.POST)
	// public ResponseEntity<Map<String, String>> login(HttpServletRequest request,
	// HttpServletResponse response,
	// @RequestBody Userdto user) {
	//
	// try {
	// Map<String, String> responseObj = new HashMap<String, String>();
	// String result = userService.validateUser(user);
	// responseObj.put("message", result);// message is key,will be used in front
	// end
	// return new ResponseEntity<Map<String, String>>(responseObj, HttpStatus.OK);
	//
	// } catch (Exception e) {
	// Map<String, String> responseObj = new HashMap<String, String>();
	// String result = userService.validateUser(user);
	// responseObj.put("Please contact your admin", result);
	// return new ResponseEntity<Map<String, String>>(responseObj,
	// HttpStatus.INTERNAL_SERVER_ERROR);
	// }
	// }

	@RequestMapping(value = "/plogin", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> login(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Userdto user) {

		Map<String, Object> responseObj = new HashMap<String, Object>();
		Userdetails result = userService.validateUser(user);

		if (result.getEmailId().equals("") || result.getEmailId() == null || result.getGender().equals("")||result.getGender()==null) {
			responseObj.put("can not be empty", result);
			return new ResponseEntity<Map<String, Object>>(responseObj, HttpStatus.NON_AUTHORITATIVE_INFORMATION);

		} else {

			responseObj.put("statuscode", HttpStatus.OK);
			responseObj.put("Username", result.getUserName());
			responseObj.put("Email-Id", result.getEmailId());
			responseObj.put("gender", result.getGender());
			responseObj.put("phoneNo", result.getPhoneNo());

			LOGGER.info("~~~~~~~~emailIdis:  " + user.getEmailId());
			
			return new ResponseEntity<Map<String, Object>>(responseObj, HttpStatus.OK);
		}
		// responseObj.put("message", result);// message is key,will be used in front
		// end
	}

	
	@CrossOrigin(origins = "*", allowCredentials = "", allowedHeaders = "*")
	//@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/SignUp", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> registration(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Userdto user) {
		
		 Map<String, String> responseObj = new HashMap<String, String>();
		String registrationResult = userService.validateRegistration(user);// user inside bracket is coming from
																			// @RequestBodu user
		responseObj.put("registrationmessage", registrationResult);
		return new ResponseEntity<Map<String, String>>(responseObj, HttpStatus.OK);
	}
}
