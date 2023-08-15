package com.example.springsecurity4.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String sayHello(){
        return "使用内存中的用户信息";
    }

    //指定normal 和admin 角色都可以访问的方法
    @RequestMapping("/helloUser")
    @PreAuthorize(value = "hasAnyRole('admin','normal')")
    public String helloCommonUser(){
        return "Hello 拥有normal,admin角色用户";
    }

    //指定admin可以访问的方法
    @RequestMapping("/helloAdmin")
    @PreAuthorize(value = "hasAnyRole('admin')")
    public String helloAdmin(){
        return "Hello admin角色的用户可以访问";
    }
}
