package com.example.cst438_project2;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    public Integer size = 0;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
