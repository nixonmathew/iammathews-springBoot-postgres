package com.self.portfolio.repository;

import java.util.Optional;
import com.self.portfolio.entity.UsersAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UsersAuth, Integer> {

    Optional<UsersAuth> findByUserName(String userName);
}
