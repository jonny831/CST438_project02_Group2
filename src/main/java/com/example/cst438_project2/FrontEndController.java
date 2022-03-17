package com.example.cst438_project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class FrontEndController {
    @Autowired
    private UserRepository userRepository;
    public static final String BASE_URI = "http://localhost:9090/api/";

    @RequestMapping("/")
    String home(Model model){
        return "index";
    }

    @GetMapping("/register")
    String register(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/register")
    String registerSubmit(@ModelAttribute("user") User user){
        userRepository.save(user);
        return"index";
    }

    @RequestMapping("/allUsers")
    String allUsers(Model model){
        String uri = BASE_URI + "allUsers";
        RestTemplate restTemplate = new RestTemplate();

        User[] users = restTemplate.getForObject(uri, User[].class);

        model.addAttribute("users", users);

        return "allUsers";
    }

    @RequestMapping("/addItem")
    String addItem(Model model){
        String uri = BASE_URI + "addItem";
        RestTemplate restTemplate = new RestTemplate();

        Item[] items = restTemplate.getForObject(uri, Item[].class);

        model.addAttribute("items", items);

        return "addItem";
    }

}
