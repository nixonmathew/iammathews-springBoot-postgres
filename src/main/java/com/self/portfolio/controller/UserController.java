package com.self.portfolio.controller;

import java.util.List;
import java.util.Optional;

import com.self.portfolio.dto.AuthenticationRequest;
import com.self.portfolio.dto.AuthenticationResponse;
import com.self.portfolio.dto.IndustrySearchRequest;
import com.self.portfolio.dto.SearchResponse;
import com.self.portfolio.service.MyUserDetailService;
import com.self.portfolio.service.UserService;
import com.self.portfolio.util.JwtUtil;
import com.self.portfolio.entity.Users;
import com.self.portfolio.entity.UsersAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping(value = "/authenticate")
    @ApiOperation(value = "Generate token if valid user", notes = "Authenticating and returning a jwt token if valid", response = ResponseEntity.class)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest req) throws Exception {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        } catch (BadCredentialsException e) {
            LOGGER.warn("Authentication Failed" + e);
            System.out.println(e);
            throw new Exception("Incorrect Username or Password", e);
        }
        final UserDetails userDetails = myUserDetailService.loadUserByUsername(req.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }

    @GetMapping(value = "/users")
    public List<Users> getUsers() {
        LOGGER.info("Controller - Getting users started");
        return userService.getUsers();
    }

    @GetMapping(value = "/users/sort/{col}/sortDir/{sortDir}")
    public List<Users> getSortedUsers(
            @ApiParam(value = "Col name and the direction of sorting", required = true) @PathVariable String col,
            @PathVariable String sortDir) {
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
    public Optional<UsersAuth> checkIfUser(@RequestBody UsersAuth usersAuth) {
        LOGGER.info("Controller - Checking If User exist started" + usersAuth);
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