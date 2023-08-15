package com.szq.springsecurity5jdbc.init;

import com.szq.springsecurity5jdbc.dao.UserInfoDao;
import com.szq.springsecurity5jdbc.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
public class JdbcInit {

    @Autowired
    private UserInfoDao userInfoDao;

//    @PostConstruct
    public void init(){

        PasswordEncoder encoder=new BCryptPasswordEncoder();

        UserInfo u=new UserInfo();
        u.setUsername("zhangsan");
        u.setPassword(encoder.encode("123456"));
        u.setRole("normal");

        userInfoDao.save(u);

        u=new UserInfo();
        u.setUsername("admin");
        u.setPassword(encoder.encode("admin"));
        u.setRole("admin");

        userInfoDao.save(u);
    }
}
