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
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemListRepository itemListRepository;


    @GetMapping(path="/allUsers")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping(path = "/allItems")
    public @ResponseBody Iterable<Item> getAllItems(){ return itemRepository.findAll();}
    @GetMapping(path = "/allItemLists")
    public @ResponseBody Iterable<ItemList> getAllItemLists(){ return itemListRepository.findAll();}

    @PostMapping(path="/addUser")
    public @ResponseBody String addUser (@RequestParam String name, @RequestParam String email, @RequestParam String password){
        User user = new User();
        UserList list = new UserList();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
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


    @GetMapping(path="/findByName")
    public @ResponseBody
    List<User> findUserByName(@RequestParam(defaultValue = "Isai Molina") String name){
        return userRepository.findUserByName(name);
    }


    @PostMapping(path="/addItem")
    public @ResponseBody String addItem (@RequestParam String itemName, @RequestParam String itemPrice, @RequestParam String itemQuantity, @RequestParam String imgUrl){
        Item item = new Item();
        ItemList list = new ItemList();
        item.setName(itemName);
        item.setPrice(Double.valueOf(itemPrice));
        item.setQuantity(Integer.valueOf(itemQuantity));
        item.setImgUrl(imgUrl);

        itemRepository.save(item);
        list.size++;
        return "item saved";
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

    @PostMapping(path = "/addItemList")
    public @ResponseBody String addItemList(@RequestParam String email, @RequestParam String listName){
        ItemList list = new ItemList();
        list.setName(listName);
        if (userRepository.existsByEmail(email)) {
            User user1 = userRepository.findByEmail(email);
            user1.addItemList(list);
            userRepository.save(user1);
            return "item list added";
        } else {
            return "email not found";
        }
    }

    @PostMapping(path = "/addItemToItemList")
    public @ResponseBody String addItemToUserList(@RequestParam String email, @RequestParam String listName, @RequestParam String itemName){
        User user1 = userRepository.findByEmail(email);
        ItemList list1 = itemListRepository.findByName(listName);
        if (itemRepository.existsByName(itemName)) {
            Item item1 = itemRepository.findByName(itemName);

            list1.addItem(item1);

            List<ItemList> listOfLists = user1.getItemLists();

            if (listOfLists.contains(list1)) {
                list1.addItem(item1);
            }
            userRepository.save(user1);
            itemListRepository.save(list1);
        }
        return "item added to user list";
    }
}
