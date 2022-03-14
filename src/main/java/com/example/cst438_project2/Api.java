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
            }
        }


        return "deleted";
    }

    @GetMapping(path="/findByName")
    public @ResponseBody
    List<User> findUserByName(@RequestParam(defaultValue = "Isai Molina") String name){
        return userRepository.findUserByName(name);
    }
}
