package com.self.portfolio.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.self.portfolio.dto.IndustrySearchRequest;
import com.self.portfolio.dto.SearchResponse;
import com.self.portfolio.entity.Industry;
import com.self.portfolio.entity.States;
import com.self.portfolio.repository.IndustryRepository;
import com.self.portfolio.repository.StateRepository;
import com.self.portfolio.repository.UserAuthRepository;
import com.self.portfolio.repository.UserRepository;
import com.self.portfolio.entity.Users;
import com.self.portfolio.entity.UsersAuth;
import com.self.portfolio.util.Utils;
import com.self.portfolio.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private IndustryRepository industryRepository;

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<Users> getUsers() {
        LOGGER.info("Impl - getUsers service started");
        List<Users> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public List<Users> getSortedUsers(String col, String sortDir) {
        LOGGER.info("Impl - getSortedUsers service started");
        List<Users> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        List<Users> sortedUsers = new ArrayList<>();
        if (col.contains("eid")) {
            if (sortDir.contains("asc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getId)).collect(Collectors.toList());
            } else if (sortDir.contains("dsc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getId).reversed())
                        .collect(Collectors.toList());
            }
        } else if (col.contains("state")) {
            if (sortDir.contains("asc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getState)).collect(Collectors.toList());
            } else if (sortDir.contains("dsc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getState).reversed())
                        .collect(Collectors.toList());
            }
        } else if (col.contains("name")) {
            if (sortDir.contains("asc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getName)).collect(Collectors.toList());
            } else if (sortDir.contains("dsc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getName).reversed())
                        .collect(Collectors.toList());
            }
        } else if (col.contains("mobile")) {
            if (sortDir.contains("asc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getMobile))
                        .collect(Collectors.toList());
            } else if (sortDir.contains("dsc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getMobile).reversed())
                        .collect(Collectors.toList());
            }
        }
        return sortedUsers;
    }

    @Override
    public List<Users> getFilteredUsers(String col, String value) {
        LOGGER.info("Impl - getFilteredUsers service started");
        List<Users> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        if (value.length() > 0) {
            List<Users> filteredUsers = new ArrayList<>();
            if (col.equals("eid")) {
                filteredUsers = users.stream().filter(res -> String.valueOf(res.getId()).contains(value))
                        .collect(Collectors.toList());
            } else if (col.equals("name")) {
                filteredUsers = users.stream().filter(res -> res.getName().toLowerCase().contains(value.toLowerCase()))
                        .collect(Collectors.toList());
            } else if (col.equals("mobile")) {
                filteredUsers = users.stream()
                        .filter(res -> res.getMobile().toLowerCase().contains(value.toLowerCase()))
                        .collect(Collectors.toList());
            } else if (col.equals("state")) {
                filteredUsers = users.stream().filter(res -> res.getState().toLowerCase().contains(value.toLowerCase()))
                        .collect(Collectors.toList());
            }
            return filteredUsers;
        } else {
            return users;
        }

    }

    @Override
    public UsersAuth checkIfUser(UsersAuth usersAuth) {
        LOGGER.info("Impl - checkIfUser service started");

        UsersAuth usersAuthData = null;
        try {
            usersAuthData = userAuthRepository.findByUsername(usersAuth.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserException("customer doesnot exist");
        }
        return usersAuthData;
    }

    @Override
    public Users createUser(Users user) {
        LOGGER.info("Impl - createUser service started");

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserException("Couldnt create your account");
        }
    }

    @Override
    public List<SearchResponse> getStates() {
        LOGGER.info("Impl - getStates service started");
        List<States> states = stateRepository.findAll();
        List<SearchResponse> DtoStates = states.stream().map(this::convertToDto).collect(Collectors.toList());
        System.out.println(DtoStates.toString());
        return DtoStates;
    }

    @Override
    public List<SearchResponse> getIndustry(IndustrySearchRequest searchRequest) {
        List<SearchResponse> refactoredIndustries = new ArrayList<>();
        LOGGER.info("Impl - getting Industry service started");
        if (searchRequest.getSearchBy() instanceof String) {
            LOGGER.info("String searched -" + searchRequest.getSearchBy());
            String strRequest = searchRequest.getSearchBy().toString().trim();
            List<Industry> industries = industryRepository.searchByDesc(strRequest.toLowerCase());
            // List<Industry> industries = industryRepository.findByisActive(true);
            return this.getFilteredIndustries(industries);
        } else if (searchRequest.getSearchBy() instanceof Integer) {
            int numericRequest = (Integer) searchRequest.getSearchBy();
            LOGGER.info("Integer searched -" + numericRequest);
            List<Industry> industries = industryRepository.searchByNumericCode(numericRequest);
            return this.getFilteredIndustries(industries);
        }
        return refactoredIndustries;
    }

    private List<SearchResponse> getFilteredIndustries(List<Industry> industries) {
        LOGGER.info("Impl- filtering industry with Description");
        System.out.println(industries.toString());
        return industries.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private <T> SearchResponse convertToDto(T qualification) {
        return Utils.convertToDto(qualification, SearchResponse.class);
    }

}
