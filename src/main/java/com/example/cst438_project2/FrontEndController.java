package com.example.cst438_project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Controller
public class FrontEndController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemListRepository itemListRepository;
    @Autowired
    private ItemRepository itemRepository;

    User loggedInUser;

    @RequestMapping("/")
    String landingPage(Model model){
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
        user.setRole("USER");
        userRepository.save(user);

        return"index";
    }

    @GetMapping("/login")
    String login(Model model){
        return "login";
    }


    @RequestMapping("/home")
    String home(Model model){
        // Authenticated User Object

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = ((MyUserDetails)principal).getUserId();
        loggedInUser = userRepository.findUserById(userId);

        List<ItemList> listsOfUser = loggedInUser.getItemLists();
        model.addAttribute("lists", listsOfUser);
        if(loggedInUser.getRole().equals("ADMIN")){
            return "adminHome";
        }
        return "home";
    }
    @RequestMapping(value="/editList", method = RequestMethod.GET)
    String editList(Model model, @RequestParam Integer id) {
        ItemList list = itemListRepository.findItemListById(id);
        List<Item> items = itemRepository.findItemsByListId(id);

        model.addAttribute("items", items);
        model.addAttribute("list", list);
        model.addAttribute("user",loggedInUser);

        return "editList";
    }

    @GetMapping("/makeList")
    String createList(Model model){
        String email = loggedInUser.getEmail();
        model.addAttribute("email", email);
        return "makeList";
    }



    @RequestMapping(value="/addItem", method = RequestMethod.GET)
    String addItem(Model model, @RequestParam Integer id){
        ItemList list = itemListRepository.findItemListById(id);
        model.addAttribute("list", list);
        model.addAttribute("user", loggedInUser);
        return "addItem";
    }



}
