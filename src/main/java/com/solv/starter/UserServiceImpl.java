package com.solv.starter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;



	@Override
	public List<Users> getUsers() {
		List <Users>users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		System.out.println(users);
		return users;
	}

	@Override
	public List<Users> getSortedUsers(String col , String sortDir) {
		List <Users> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		List<Users> sortedUsers = new ArrayList();
		if(col.contains("eid")) {
			if(sortDir.contains("asc")) {
				sortedUsers = users.stream().sorted(Comparator.comparing(Users::getEid)).collect(Collectors.toList());
			}
			else if(sortDir.contains("dsc")) {
				sortedUsers = users.stream().sorted(Comparator.comparing(Users::getEid).reversed()).collect(Collectors.toList());
			}
		}
		else if(col.contains("address")) {
			if(sortDir.contains("asc")) {
				sortedUsers = users.stream().sorted(Comparator.comparing(Users::getAddress)).collect(Collectors.toList());
			}
			else if(sortDir.contains("dsc")) {
				sortedUsers = users.stream().sorted(Comparator.comparing(Users::getAddress).reversed()).collect(Collectors.toList());
			}
		}
		else if(col.contains("name")) {
			if(sortDir.contains("asc")) {
				sortedUsers = users.stream().sorted(Comparator.comparing(Users::getName)).collect(Collectors.toList());
			}
			else if(sortDir.contains("dsc")) {
				sortedUsers = users.stream().sorted(Comparator.comparing(Users::getName).reversed()).collect(Collectors.toList());
			}
		}
		else if(col.contains("mobile")) {
			if(sortDir.contains("asc")) {
				sortedUsers = users.stream().sorted(Comparator.comparing(Users::getMobile)).collect(Collectors.toList());
			}
			else if(sortDir.contains("dsc")) {
				sortedUsers = users.stream().sorted(Comparator.comparing(Users::getMobile).reversed()).collect(Collectors.toList());
			}
		}
		return sortedUsers ;
	}
	

	@Override
	public List<Users> getFilteredUsers(String col, String value) {
		List <Users> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		System.out.println(value.length());
		System.out.println(col);
		System.out.println(col.equals("address"));
		if(value.length() > 0) {
			List <Users> filteredUsers = new ArrayList();
			if(col.equals("eid")) {
				filteredUsers = users.stream().filter(res -> String.valueOf(res.getEid()).contains(value)).collect(Collectors.toList());				
			}
			else if(col.equals("name")) {
				filteredUsers = users.stream().filter(res -> res.getName().toLowerCase().contains(value.toLowerCase())).collect(Collectors.toList());
			}
			else if(col.equals("mobile")) {
				filteredUsers = users.stream().filter(res -> res.getMobile().toLowerCase().contains(value.toLowerCase())).collect(Collectors.toList());
			}
			else if(col.equals("address")) {
				System.out.println(value + "address");
				filteredUsers = users.stream().filter(res -> res.getAddress().toLowerCase().contains(value.toLowerCase())).collect(Collectors.toList());				
			}
			return filteredUsers;			
		}
		else {
			return users;
		}

	}


	@Override
	public Users createUser(Users user) {
		
		try {	
		Users user1 = userRepository.save(user);
		System.out.println(user1);
		return user1;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
				
		}				
	}


}
