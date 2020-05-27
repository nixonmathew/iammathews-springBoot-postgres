package com.self.portfolio.repository;

import com.self.portfolio.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<Users, Integer>{

//    @Query("SELECT u from Users u WHERE LOWER(u.address) like %:address%")
//    List<Users> getAddressWithText(@Param("address") String address);

}
