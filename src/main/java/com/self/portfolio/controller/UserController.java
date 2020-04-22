package com.self.portfolio.controller;

import java.util.List;

import com.self.portfolio.dto.StateSearchResponse;
import com.self.portfolio.service.UserService;
import com.self.portfolio.entity.Users;
import com.self.portfolio.entity.UsersAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    @CrossOrigin
    public List<Users> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/users/sort/{col}/sortDir/{sortDir}")
    @CrossOrigin
    public List<Users> getSortedUsers(@PathVariable String col, @PathVariable String sortDir) {
        return userService.getSortedUsers(col, sortDir);
    }

    @GetMapping(value = "/users/filter/{col}/{filterBy}")
    @CrossOrigin
    public List<Users> getFilteredUsers(@PathVariable String col, @PathVariable String filterBy) {
        return userService.getFilteredUsers(col, filterBy);
    }

    @PostMapping(value = "/users")
    @CrossOrigin
    public Users createUser(@RequestBody Users users) {
        System.out.println(users);
        return userService.createUser(users);
    }

    @PostMapping(value = "/users/login")
    @CrossOrigin
    public UsersAuth checkIfUser(@RequestBody UsersAuth usersAuth) {
        System.out.println(usersAuth);
        return userService.checkIfUser(usersAuth);
    }

    @GetMapping(value = "/users/address/{filterBy}")
    @CrossOrigin
    public List<Users> getAddressWithText(@PathVariable String filterBy) {
        return userService.getAddressWithText(filterBy);
    }

    @GetMapping(value ="/states")
	public List<StateSearchResponse> getStates(){
    	return userService.getStates();
	}
}