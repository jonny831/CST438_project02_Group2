package com.example.cst438_project2;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {


    User findUserById(Integer id);

    @Query(value = "SELECT * FROM User u WHERE u.name LIKE %:name%",
            countQuery = "Select count(*) from User",
            nativeQuery = true)
    List<User> findUserByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.email =?1")
    User findByEmail(String email);

    boolean existsByEmail(String email);
}

