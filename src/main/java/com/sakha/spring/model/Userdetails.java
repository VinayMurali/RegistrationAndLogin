package com.sakha.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Userdetails is table name
@Entity
@Table(name = "userdetails")
public class Userdetails {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // for auto generation
	private int uid;

	@Column(name = "Age")
	private int age;

	@Column(name = "phoneNo")
	private String phoneNo;

	@Column(name = "userName")
	public String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "emailId")
	private String emailId;
	
	@Column(name = "gender")
	private String gender;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String setPhoneNo(String i) {
		return this.phoneNo = i;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public String setEmailId(String emailId) {
		return this.emailId = emailId;
	}
}
