package com.solv.starter;

import org.springframework.data.repository.CrudRepository;

public interface UserAuthRepository extends CrudRepository<UsersAuth, Integer>{

    UsersAuth findByUsername(String userName);
}
