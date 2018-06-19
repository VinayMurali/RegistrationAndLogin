package com.sakha.spring.dto;

import java.util.logging.Logger;

import javax.transaction.Transactional;

//write only getparameters value 

public class Userdto {

	public String userName;
	private int age;
	private String password;
	private String emailId;
	private String gender;
	private String phoneNo;
	private static final Logger LOGGER = Logger.getLogger(Userdto.class.getName());

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public String getEmailId() {
		return emailId;
	}

	public String setEmailId(String emailId) {

		return this.emailId = emailId;

	}

	public String getGender() {
		return gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

}
