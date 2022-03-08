package com.example.cst438_project2;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Integer> {

    @Query(value = "SELECT * FROM User u WHERE u.name LIKE %:name%",
    countQuery = "Select count(*) from User",
        nativeQuery = true)
    List<User> findUserByName(
            @Param("name") String name
    );

}
