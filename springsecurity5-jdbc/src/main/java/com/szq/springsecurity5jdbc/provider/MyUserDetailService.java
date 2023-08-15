package com.szq.springsecurity5jdbc.provider;

import com.szq.springsecurity5jdbc.dao.UserInfoDao;
import com.szq.springsecurity5jdbc.pojo.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("myUserDetailService")
public class MyUserDetailService implements UserDetailsService {

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=null;
        UserInfo userInfo=null;
        if (username!=null){
             userInfo = userInfoDao.findByUsername(username);

             if (userInfo!=null){
                 List<GrantedAuthority> list=new ArrayList<>();
                 //角色必须以ROLE_开头
                 GrantedAuthority authority=new SimpleGrantedAuthority("ROLE_"+userInfo.getRole());

                 //创建User对象
                  user=new User(userInfo.getUsername(),userInfo.getPassword(),list);
             }

        }

       return user;

    }
}
