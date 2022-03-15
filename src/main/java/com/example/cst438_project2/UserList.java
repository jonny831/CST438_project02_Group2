package com.example.cst438_project2;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserList {
    @Id
    private Integer userId;
    private Integer listId;
    private String name;
    public Integer size = 0;

    public UserList() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

}
