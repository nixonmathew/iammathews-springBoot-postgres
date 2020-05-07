package com.self.portfolio.controller;

import java.util.List;

import com.self.portfolio.dto.Greeting;
import com.self.portfolio.dto.HelloMessage;
import com.self.portfolio.dto.IndustrySearchRequest;
import com.self.portfolio.dto.SearchResponse;
import com.self.portfolio.service.UserService;
import com.self.portfolio.trial.GenericClass;
import com.self.portfolio.entity.Industry;
import com.self.portfolio.entity.Users;
import com.self.portfolio.entity.UsersAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Value("${server.port}")
    private String getServerPort;

    @GetMapping("/")
    public String index() {
        LOGGER.info("started");
        GenericClass<Greeting, HelloMessage> gen = new GenericClass<>(new Greeting(), new HelloMessage());
        return gen.toString();
    }

    @GetMapping("/envDetails")
    public String getEnvDetails() {
        return env.toString();
    }

    @GetMapping(value = "/users")
    public List<Users> getUsers() {
        LOGGER.info("Controller - Getting users started");
        return userService.getUsers();
    }

    @GetMapping(value = "/users/sort/{col}/sortDir/{sortDir}")
    public List<Users> getSortedUsers(@PathVariable String col, @PathVariable String sortDir) {
        LOGGER.info("Controller - Sorting users started");
        return userService.getSortedUsers(col, sortDir);
    }

    @GetMapping(value = "/users/filter/{col}/{filterBy}")
    public List<Users> getFilteredUsers(@PathVariable String col, @PathVariable String filterBy) {
        LOGGER.info("Controller - Filtering Users get operation started");
        return userService.getFilteredUsers(col, filterBy);
    }

    @PostMapping(value = "/users")
    public Users createUser(@RequestBody Users users) {
        LOGGER.info("Controller - Creating Users started");
        System.out.println(users);
        return userService.createUser(users);
    }

    @PostMapping(value = "/users/login")
    public UsersAuth checkIfUser(@RequestBody UsersAuth usersAuth) {
        LOGGER.info("Controller - Checking If User exist started");
        // System.out.println(usersAuth);
        // Long time = new Long(5000);
        // try {
        // Thread.sleep(time);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        return userService.checkIfUser(usersAuth);
    }

    @GetMapping(value = "/states")
    public List<SearchResponse> getStates() {
        LOGGER.info("Controller - Getting List of States controller started");
        return userService.getStates();
    }

    @PostMapping(value = "/industry")
    public List<SearchResponse> getIndustry(@RequestBody IndustrySearchRequest searchRequest) {
        LOGGER.info("Controller - Getting Industry controller started");
        return userService.getIndustry(searchRequest);
    }
}