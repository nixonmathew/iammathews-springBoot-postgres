package com.solv.starter;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class UserController {

	@Autowired
	private UserService userService ;
	
	@GetMapping(value= "/users")
	@CrossOrigin(origins = "http://localhost:4200")
    public List<Users> getUsers() {
		return userService.getUsers();
	}
	
	@GetMapping(value= "/users/sort/{col}/sortDir/{sortDir}")
	@CrossOrigin(origins = "http://localhost:4200")
    public List<Users> getSortedUsers(@PathVariable String col , @PathVariable String sortDir) {
		return userService.getSortedUsers(col,sortDir);
	}

	@GetMapping(value= "/users/filter/{col}/{filterBy}")
	@CrossOrigin(origins = "http://localhost:4200")
    public List<Users> getFilteredUsers(@PathVariable String col,@PathVariable String filterBy) {
		return userService.getFilteredUsers(col,filterBy);
	}
		
	
	
	@PostMapping(value = "/users")
	@CrossOrigin(origins = "http://localhost:4200")
    public Users createUser() {
		UsersAuth usersAuth = new UsersAuth();
		usersAuth.setUsername("vikram");
		usersAuth.setPassword("passVik");

		Users newUser = new Users();
		newUser.setName("VLC");
		newUser.setMobile("9788780900");
		newUser.setAddress("sample address");
		newUser.setUser_auth(usersAuth);

		System.out.println(newUser);
		return userService.createUser(newUser);
	}
		
	
}