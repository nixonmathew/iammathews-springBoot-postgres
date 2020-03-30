package com.solv.starter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Users getUserDetails() {
	return null;
	}

	@Override
	public List<Users> getUsers() {
		List <Users>users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		System.out.println(users);
		return users;
	}

	@Override
	public void createUser(Users user) {
		Users user1 = userRepository.save(user);
		System.out.println(user1);
		
	}

}
