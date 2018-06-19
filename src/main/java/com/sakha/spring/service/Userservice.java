package com.sakha.spring.service;

import com.sakha.spring.dto.Userdto;
import com.sakha.spring.model.Userdetails;

public interface Userservice {

	public String validateUser(String userName, String password);

	public Userdetails validateUser(Userdto userdto);

	public String validateRegistration(Userdto userdto);

	public String getUser(Userdto userdto);

}
