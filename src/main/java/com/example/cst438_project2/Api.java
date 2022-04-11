package com.example.cst438_project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@Controller
@EnableGlobalMethodSecurity(prePostEnabled=true)
@RequestMapping(path="/api")
public class Api {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemListRepository itemListRepository;


    @GetMapping(path = "/allItems")
    public @ResponseBody Iterable<Item> getAllItems(){ return itemRepository.findAll();}
    @GetMapping(path = "/allItemLists")
    public @ResponseBody Iterable<ItemList> getAllItemLists(){ return itemListRepository.findAll();}



    @RequestMapping("/allUsers")
    String allUsers(Model model){

        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "allUsers";
    }



    @PostMapping(path="/addUser")
    public @ResponseBody String addUser (@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String role){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);
        return "saved";
    }

    @PostMapping (path="/deleteUser")
    public String deleteUser (@RequestParam Integer userId){
        User user = userRepository.findUserById(userId);
        userRepository.delete(user);
        return "redirect:/allUsers";
    }


    @GetMapping(path="/findByName")
    public @ResponseBody
    Iterable<User> findUserByName(@RequestParam String name){
        return userRepository.findUserByName(name);
    }

    @GetMapping(path = "/item")
    public @ResponseBody Item getItem(@RequestParam Integer itemId) {
        return itemRepository.findItemById(itemId);
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
    public String addItemList(@RequestParam String email, @RequestParam String listName){
        ItemList list = new ItemList();
        list.setName(listName);

        User user1 = userRepository.findByEmail(email);
        user1.addItemList(list);
        userRepository.save(user1);

        return "redirect:/home";

    }
    @PostMapping(path = "/deleteItemList")
    public String deleteItemList(@RequestParam String email, @RequestParam Integer id){
        User user = userRepository.findByEmail(email);
        ItemList list = itemListRepository.findItemListById(id);
        List<ItemList> itemLists = user.getItemLists();
        itemLists.remove(list);
        user.setItemLists(itemLists);
        itemListRepository.delete(list);

        return "redirect:/home";
    }

    @PostMapping(path = "/addItemToUserList")
    public String addItemToUserList(@RequestParam String email, @RequestParam Integer listId, @RequestParam String itemName, @RequestParam Double itemPrice, @RequestParam Integer itemQuantity, @RequestParam String imageUrl){
        User user = userRepository.findByEmail(email);
        ItemList list = itemListRepository.findItemListById(listId);
        Item item = new Item();
        item.setName(itemName);
        item.setPrice(itemPrice);
        item.setQuantity(itemQuantity);
        item.setImgUrl(imageUrl);
        itemRepository.save(item);
        list.addItem(item);
        itemListRepository.save(list);

        String url = "redirect:/editList?id=" + list.getId();
        return url;
    }

    @PostMapping(path = "/deleteItemFromUserList")
    public String deleteItemFromUserList(@RequestParam Integer listId, @RequestParam Integer itemId){
        ItemList list = itemListRepository.findItemListById(listId);
        Item item = itemRepository.findItemById(itemId);
        List<Item> items = list.getItems();
        items.remove(item);
        list.setItems(items);
        itemRepository.delete(item);

        String url = "redirect:/editList?id=" + list.getId();
        return url;
    }
}
