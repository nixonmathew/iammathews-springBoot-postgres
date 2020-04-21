package com.solv.starter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthRepository userAuthRepository;


    @Override
    public List<Users> getUsers() {
        List<Users> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public List<Users> getSortedUsers(String col, String sortDir) {
        List<Users> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        List<Users> sortedUsers = new ArrayList();
        if (col.contains("eid")) {
            if (sortDir.contains("asc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getId)).collect(Collectors.toList());
            } else if (sortDir.contains("dsc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getId).reversed()).collect(Collectors.toList());
            }
        } else if (col.contains("address")) {
            if (sortDir.contains("asc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getAddress)).collect(Collectors.toList());
            } else if (sortDir.contains("dsc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getAddress).reversed()).collect(Collectors.toList());
            }
        } else if (col.contains("name")) {
            if (sortDir.contains("asc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getName)).collect(Collectors.toList());
            } else if (sortDir.contains("dsc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getName).reversed()).collect(Collectors.toList());
            }
        } else if (col.contains("mobile")) {
            if (sortDir.contains("asc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getMobile)).collect(Collectors.toList());
            } else if (sortDir.contains("dsc")) {
                sortedUsers = users.stream().sorted(Comparator.comparing(Users::getMobile).reversed()).collect(Collectors.toList());
            }
        }
        return sortedUsers;
    }


    @Override
    public List<Users> getFilteredUsers(String col, String value) {
        List<Users> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        if (value.length() > 0) {
            List<Users> filteredUsers = new ArrayList();
            if (col.equals("eid")) {
                filteredUsers = users.stream().filter(res -> String.valueOf(res.getId()).contains(value)).collect(Collectors.toList());
            } else if (col.equals("name")) {
                filteredUsers = users.stream().filter(res -> res.getName().toLowerCase().contains(value.toLowerCase())).collect(Collectors.toList());
            } else if (col.equals("mobile")) {
                filteredUsers = users.stream().filter(res -> res.getMobile().toLowerCase().contains(value.toLowerCase())).collect(Collectors.toList());
            } else if (col.equals("address")) {
                filteredUsers = users.stream().filter(res -> res.getAddress().toLowerCase().contains(value.toLowerCase())).collect(Collectors.toList());
            }
            return filteredUsers;
        } else {
            return users;
        }

    }

    @Override
    public UsersAuth checkIfUser(UsersAuth usersAuth) {

        UsersAuth usersAuthData = null;
        try {
            usersAuthData = userAuthRepository.findByUsername(usersAuth.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Logged in user detail is " + usersAuthData.toString());
        return usersAuthData;
    }


    @Override
    public Users createUser(Users user) {

        try {
            Users user1 = userRepository.save(user);
            return user1;
        } catch (Exception e) {
            return null;

        }
    }

    @Override
    public List<Users> getAddressWithText(String address) {
        List<Users> users = new ArrayList<>();
        try {
            users = userRepository.getAddressWithText(address);
        } catch (Exception e) {

        }
        return users;
    }

}