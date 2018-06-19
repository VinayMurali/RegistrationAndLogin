package com.sakha.spring.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sakha.spring.dao.UserdetailsDao;
import com.sakha.spring.dto.Userdto;
import com.sakha.spring.model.Userdetails;
import com.sakha.spring.service.Userservice;

@Service
public class Userserviceimpl implements Userservice {

	@Autowired
	private UserdetailsDao userDetailsDao;

	// for get
	@Override
	@Transactional
	public String validateUser(String userName, String password) {

		if (userName == null || userName.equals("")) {
			return "Can not be empty";
		}
		Userdetails ud = userDetailsDao.forLogin(userName, password);
		if (ud != null) {
			return "Loign  Sucessfull";
		} else
			return "Login Not Sucessfull";
	}

	// @Override
	// @Transactional
	// public String validateUser(Userdto userdto) {// accepting from controller
	//
	// if (userdto.getUserName() == null || userdto.equals("")) {
	// return "Can not be empty";
	// }
	// Userdetails ud = userDetailsDao.forLogin(userdto.getUserName(),
	// userdto.getPassword());
	// if (ud != null) {
	// String un = userdto.getUserName();
	// Userdetails userdetails = new Userdetails();
	// int age = userdetails.getAge();
	// String eid = userdetails.getEmailId();
	// String phNo = userdetails.getPhoneNo();
	// String gender = userdetails.getGender();
	// return "Status:" + HttpStatus.OK + "\nLogined Sucessfully and username is : "
	// + un ;
	// } else
	// return "Login Not Sucessfull";
	// }

	// @Override
	// @Transactional
	// public int validateUser(Userdto userdto) {// accepting from controller
	// //Userdto userdto=new Userdto();
	// if (userdto.getUserName() == null || userdto.equals("")) {
	// return 0;
	// //return "Can not be empty";
	// }
	// Userdetails ud = userDetailsDao.forLogin(userdto.getUserName(),
	// userdto.getPassword());
	// if (ud != null) {
	// return 1;
	// //return "Status:" + HttpStatus.OK + "\nLogined Sucessfully and username is :
	// " + un ;
	// } else
	// return -1;
	// //return "Login Not Sucessfull";
	// }

	@Override
	@Transactional
	public Userdetails validateUser(Userdto userdto) {// accepting from controller
		Userdetails userDetails;
		if (userdto.getUserName() == null || userdto.equals("")) {
			return null;
			// return "Can not be empty";
		}
		return userDetailsDao.forLogin(userdto.getUserName(), userdto.getPassword());
		
	}

	@Override
	@Transactional
	public String validateRegistration(Userdto userdto) {

		Userdetails userdetails = new Userdetails();// to pass data from service to dao
		userdetails.setAge(userdto.getAge());
		userdetails.setUserName(userdto.getUserName());
		userdetails.setPassword(userdto.getPassword());
		userdetails.setEmailId(userdto.getEmailId());
		userdetails.setGender(userdto.getGender());
		userdetails.setPhoneNo(userdto.getPhoneNo());
		int ee = userDetailsDao.registration(userdetails);
		if (ee > 0) {
			return "registred successfully";
		} else {
			return "registratoin failed";
		}
	}

	@Override
	@Transactional
	public String getUser(Userdto userdto) {

		if (userdto.getUserName() == null || userdto.getUserName().equals("") || userdto.getEmailId() == null
				|| userdto.getEmailId().equals("") || userdto.getPhoneNo() == null || userdto.getPhoneNo().equals("")
				|| userdto.getGender() == null || userdto.getGender().equals("")) {
			return "Can not be empty";
		}
		Userdetails ud1 = userDetailsDao.getUserDetails(userdto.getUserName());

		String uname = userdto.getUserName();
		String phNo = userdto.getPhoneNo();
		String eid = userdto.getEmailId();
		String gender = userdto.getGender();
		return eid;

	}

}
