package com.example.cst438_project2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
public class FrontEndController {

    public static final String BASE_URI = "http://localhost:9090/api/";

    @RequestMapping("/")
    String home(Model model){
        return "home";
    }

    @RequestMapping("/allUsers")
    String allUsers(Model model){
        String uri = BASE_URI + "allUsers";
        RestTemplate restTemplate = new RestTemplate();

        User[] users = restTemplate.getForObject(uri, User[].class);

        model.addAttribute("users", users);

        return "allUsers";
    }

}
