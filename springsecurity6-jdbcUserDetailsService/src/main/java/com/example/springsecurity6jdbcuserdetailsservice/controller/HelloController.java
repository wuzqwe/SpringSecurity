package com.example.springsecurity6jdbcuserdetailsservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    private String hello(){
        return "Hello Security";
    }
}
