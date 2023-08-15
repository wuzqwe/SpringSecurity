package com.example.springsecurity7userrole.service;

import com.example.springsecurity7userrole.mapper.RoleMapper;
import com.example.springsecurity7userrole.mapper.SysUserMapper;
import com.example.springsecurity7userrole.pojo.SysRole;
import com.example.springsecurity7userrole.pojo.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据username获取SysUser
        SysUser user= sysUserMapper.selectSysUser(username);
        System.out.println("user:"+user);
        List<GrantedAuthority> list=new ArrayList<>();
        if (user!=null){
            //根据userid,获取role
            List<SysRole> roleList = roleMapper.selectRoleByUser(user.getId());
            System.out.println("roleList:"+roleList);
            String roleName="";
            for (SysRole role: roleList){
                roleName=role.getName();
                GrantedAuthority authority=new SimpleGrantedAuthority("ROLE_"+roleName);
                list.add(authority);
            }
            user.setAuthorities(list);
        }
        return user;
    }
}
