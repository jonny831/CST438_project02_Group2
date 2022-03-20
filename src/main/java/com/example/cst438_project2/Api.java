package com.example.cst438_project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/api")
public class Api {

    @Autowired
    private UserRepository userRepository;
    private ItemRepository itemRepository;

    @GetMapping(path="/allUsers")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping(path="/addUser")
    public @ResponseBody String addUser (@RequestParam String name, @RequestParam String email){
        User user = new User();
        UserList list = new UserList();
        user.setName(name);
        user.setEmail(email);

        userRepository.save(user);
        list.size++;
        return "saved";
    }

    @PostMapping(path="/deleteUser")
    public @ResponseBody String deleteUser (@RequestParam String name){
        User user = new User();
        UserList list = new UserList();
        for (int i = 0; i < list.size; i++) {
            if (user.getName().equals(name)) {
                userRepository.delete(user);
                list.size--;
            }
        }


        return "deleted";
    }
    @PostMapping(path="/deleteItem")
    public @ResponseBody String deleteItem (@RequestParam String name){
        Item item = new Item();
        ItemList itemList = new ItemList();
        for (int i = 0; i < itemList.size; i++) {
            if (item.getName().equals(name)) {
                itemRepository.delete(item);
                itemList.size--;
            }
        }


        return "deleted Item";
    }

    @GetMapping(path="/findByName")
    public @ResponseBody
    List<User> findUserByName(@RequestParam(defaultValue = "Isai Molina") String name){
        return userRepository.findUserByName(name);
    }

    @GetMapping(path="/addItem")
    public @ResponseBody Iterable<Item> getAllItems(){
        return itemRepository.findAll();
    }

    @PostMapping(path="/addItem")
    public @ResponseBody String addItem (@RequestParam String itemName, @RequestParam String itemPrice, @RequestParam String itemQuantity){
        Item item = new Item();
        ItemList list = new ItemList();
        item.setName(itemName);
        item.setPrice(Double.valueOf(itemPrice));
        item.setQuantity(Integer.valueOf(itemQuantity));

        itemRepository.save(item);
        list.size++;
        return "saved";
    }
}
