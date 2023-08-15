package com.example.springsecurity7userrole.filter;

import com.example.springsecurity7userrole.common.MyFailureHandler;
import com.example.springsecurity7userrole.exception.VerificationException;
import com.example.springsecurity7userrole.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Slf4j
public class VerificationCodeFilter extends OncePerRequestFilter {

    private MyFailureHandler failureHandler=new MyFailureHandler();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("==输出===");

      //只有是login操作，才需要这个过滤器参与验证码的使用
        String uri=request.getRequestURI();
        log.info("拦截路径:{}",uri);
        if (!"/login".equals(uri)){
            //过滤器正常执行，不参与验证码操作
            filterChain.doFilter(request,response);
        }else {
            try{
                //验证:code是否正确
                verifcationCode(request);
                //如果验证通过，过滤器正常执行
                filterChain.doFilter(request,response);
            }catch (VerificationException e){
                Result result = new Result();
                result.setCode(1);
                result.setError(1002);
                result.setMsg("验证码错误!!!");
                failureHandler.setResult(result);
                failureHandler.onAuthenticationFailure(request,response,e);
            }
        }

    }

    private void verifcationCode(HttpServletRequest request){
        //获取请求中的code
        String requestCode = request.getParameter("code");
        HttpSession session=request.getSession();
        String sessionCode="";
        //获取session中的code
        Object attr = session.getAttribute("code");
        if (attr!=null){
            sessionCode=(String) attr;
        }
        //处理逻辑
        if (!StringUtils.isEmpty(sessionCode)){
            //在session中的code,用户看到这个code
            //如果能看到这段代码，说明用户已经发起了登录的请求的
            //session中的现在这个code就是无效的
            session.removeAttribute("code");
        }

        //判断code是否正确
        if (StringUtils.isEmpty(requestCode)||StringUtils.isEmpty(sessionCode)||
                !requestCode.equals(sessionCode)){
            //失败
            throw new VerificationException();
        }
    }
}
