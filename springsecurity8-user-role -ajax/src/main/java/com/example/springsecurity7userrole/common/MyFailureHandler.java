package com.example.springsecurity7userrole.common;

import com.example.springsecurity7userrole.vo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class MyFailureHandler implements AuthenticationFailureHandler {

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //验证用户登录失败时执行的方法
        response.setContentType("text/json;charset=utf-8");
        if (result==null){
            Result localresult = new Result();
            localresult.setCode(1);
            localresult.setError(1001);
            localresult.setMsg("登录失败");
            result=localresult;
        }


        OutputStream out=response.getOutputStream();
        ObjectMapper om = new ObjectMapper();
        om.writeValue(out,result);
        out.flush();
        out.close();
    }
}
