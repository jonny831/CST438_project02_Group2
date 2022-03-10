package com.example.cst438_project2;

import javax.persistence.*;

@Entity
public class UserList {
    @Id
    private Integer userId;
    private Integer listId;

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
