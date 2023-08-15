package com.example.springsecurity7userrole.common;

import com.example.springsecurity7userrole.vo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * authentication spring security 框架成功后的封装类
 */
@Component
public class MySuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //登录的用户信息验证成功后执行的方法
        response.setContentType("text/json;charset=utf-8");
        Result result = new Result();
        result.setCode(0);
        result.setError(1000);
        result.setMsg("登录成功");

        OutputStream out=response.getOutputStream();
        ObjectMapper om = new ObjectMapper();
        om.writeValue(out,result);
        out.flush();
        out.close();
    }


}
