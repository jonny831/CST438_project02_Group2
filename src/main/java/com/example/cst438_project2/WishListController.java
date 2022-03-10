package com.example.cst438_project2;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishListController {

    @GetMapping("/allLists")
    List<String> all(){
        List<String> temp = new ArrayList<>();
        temp.add("foo");
        temp.add("bar");
        temp.add("bar");
        return temp;



    }



}
