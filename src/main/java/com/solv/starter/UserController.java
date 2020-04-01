package com.solv.starter;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

	@Autowired
	private UserService userService ;
	

	@GetMapping("/users")
	@CrossOrigin(origins = "http://localhost:4200")
    public List<Users> getUsers() {
		return userService.getUsers();
	}
	
	@PostMapping(path = "/users")
	public void createUser(@RequestBody Users user) {
		userService.createUser(user);
	}
	
}