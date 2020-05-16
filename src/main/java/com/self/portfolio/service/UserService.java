package com.self.portfolio.service;

import java.util.List;
import java.util.Optional;

import com.self.portfolio.dto.IndustrySearchRequest;
import com.self.portfolio.dto.SearchResponse;
import com.self.portfolio.entity.Users;
import com.self.portfolio.entity.UsersAuth;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	public List<Users> getUsers();
	public Users createUser(Users user);
	public List<Users> getSortedUsers(String col,String dir);
	public List<Users> getFilteredUsers(String col, String value);
	public Optional <UsersAuth> checkIfUser(UsersAuth usersAuth);
	public List<SearchResponse> getStates();
	public List<SearchResponse> getIndustry(IndustrySearchRequest searchRequest);
}
