package com.example.springsecurity7userrole.mapper;

import com.example.springsecurity7userrole.pojo.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {

    int insertSysUser(SysUser user);

    //根据账号名称获取用户信息
    SysUser selectSysUser(String username);
}
