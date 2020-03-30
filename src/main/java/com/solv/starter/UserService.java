package com.solv.starter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

public interface UserService {

	public Users getUserDetails();
	public List<Users> getUsers();
	public void createUser(Users user);
}
