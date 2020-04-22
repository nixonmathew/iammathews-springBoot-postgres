package com.self.portfolio.repository;

import com.self.portfolio.entity.UsersAuth;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthRepository extends CrudRepository<UsersAuth, Integer>{

    UsersAuth findByUsername(String userName);
}
