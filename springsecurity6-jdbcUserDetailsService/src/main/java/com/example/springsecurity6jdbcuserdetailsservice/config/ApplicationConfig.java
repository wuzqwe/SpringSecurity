package com.example.springsecurity6jdbcuserdetailsservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {

    //通过spring容器注入DataSource
    @Resource
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        return encoder;
    }

    //创建JdbcUserDetailService对象
    @Bean
    public UserDetailsService jdbcUserDetailsService(){

        System.out.println("====dataSource===="+dataSource);
        PasswordEncoder encoder = passwordEncoder();

        //初始化数据源DataSource----jdbcTemblate对象
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        //如果数据库中存在账号则不添加
        if (!manager.userExists("admin")) {
            manager.createUser(User.withUsername("admin")
                    .password(encoder.encode("admin"))
                    .roles("ADMIN","USER","MANGER").build());
        }

        if (!manager.userExists("zs")) {
            manager.createUser(User.withUsername("zs")
                    .password(encoder.encode("123"))
                    .roles("USER").build());
        }

        if (!manager.userExists("lisi")){
            manager.createUser(User.withUsername("lisi")
                    .password(encoder.encode("456"))
                    .roles("USER").build());
        }


        return manager;
    }

}
