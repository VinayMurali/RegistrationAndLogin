package com.sakha.spring.dao;

import com.sakha.spring.model.Userdetails;

public interface UserdetailsDao {
	
	public Userdetails forLogin(String userName,String password);
	public int registration(Userdetails userdetails);
	public Userdetails getUserDetails(String userName);
}