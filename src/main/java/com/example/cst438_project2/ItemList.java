package com.example.cst438_project2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ItemList {

    public Integer size = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_list_id")
    private Integer id;

    private String name;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="item_list_id")
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
