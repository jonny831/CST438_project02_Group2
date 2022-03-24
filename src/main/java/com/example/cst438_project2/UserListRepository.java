package com.example.cst438_project2;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.OneToMany;

public interface UserListRepository extends CrudRepository<UserList,Integer> {

}
