package com.project2.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Cst438Project02Application {
    @RequestMapping("/")

    @ResponseBody

    String home(@RequestParam(defaultValue = "test")String id){
        if(id.equals("21233")){
            return "error?";
        }
        return "Hello world! param== " + id;
    }

    @RequestMapping(value = "/name")
    @ResponseBody
    String home() {
        return "Something else";
    }

    public static void main(String[] args) {
        SpringApplication.run(Cst438Project02Application.class, args);
    }

}
