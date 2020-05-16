package com.self.portfolio.service;

import java.util.Optional;

import com.self.portfolio.dto.MyUserDetails;
import com.self.portfolio.entity.UsersAuth;
import com.self.portfolio.repository.UserAuthRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    // public class MyUserDetailService {

    private static Logger LOGGER = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Calling User auth table with username" + username);
        Optional<UsersAuth> user = userAuthRepository.findByUserName(username);
        if (user.isPresent()) {
            LOGGER.info("Printing user retrieved from DB ----------------");
            System.out.println(user.toString());
        }
        user.orElseThrow(() -> new UsernameNotFoundException("UserName doesnot exist:" + username));

        return user.map(MyUserDetails::new).get();
    }

}
