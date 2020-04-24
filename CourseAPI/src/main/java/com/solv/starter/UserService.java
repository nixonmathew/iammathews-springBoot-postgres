package com.solv.starter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

	public List<Users> getUsers();
	public Users createUser(Users user);
	public List<Users> getSortedUsers(String col,String dir);
	public List<Users> getFilteredUsers(String col, String value);
	public UsersAuth checkIfUser(UsersAuth usersAuth);
//	public List<Users> getAddressWithText(String address);

}
