package com.example.springsecurity7userrole.config;

import com.example.springsecurity7userrole.filter.VerificationCodeFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    //把SuccessHandler ,FailureHandler注入进来
    @Resource
    private AuthenticationSuccessHandler successHandler;

    @Resource
    private AuthenticationFailureHandler failureHandler;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("=========configure HttpSecurity===========");
        http.authorizeHttpRequests()
                //哪些地址可以直接访问，和登录有关的
                .antMatchers("/index","/myajax.html","/login","/js/**","/captcha/**").permitAll()  //允许这个页面不需要安全验证
                .antMatchers("/access/user/**").hasRole("USER")
                .antMatchers("/access/read/**").hasRole("READ")
                .antMatchers("/access/admin/**").hasRole("ADMIN")//用户验证
                .anyRequest().authenticated()
                .and()
                .formLogin()
                /*.usernameParameter("username")
                .passwordParameter("password")*///设置自定义的参数名字
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .loginPage("/myajax.html")  // 登录的自定义视图页面
                .loginProcessingUrl("/login")//form中登录的访问url地址
                .and()
                .csrf().disable();  //跨域访问的安全设置先禁用

        //在框架的过滤器框架中，加入一个自定义过滤器
        http.addFilterBefore(new VerificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
