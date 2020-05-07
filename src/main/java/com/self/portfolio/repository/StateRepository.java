package com.self.portfolio.repository;

import com.self.portfolio.entity.States;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<States,Long> {

}
