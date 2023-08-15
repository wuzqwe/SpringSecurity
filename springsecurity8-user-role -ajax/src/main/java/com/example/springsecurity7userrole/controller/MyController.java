package com.example.springsecurity7userrole.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/access/user")
    public String sayUser(){
        return "zs 是 user 角色";
    }

    @GetMapping("/access/read")
    public String sayRead(){
        return "lisi 是Read 角色";
    }

    @GetMapping("/access/admin")
    public String sayAdmin(){
        return "admin 是user,admin 角色";
    }
}
