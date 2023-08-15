package com.example.springsecurity7userrole.mapper;

import com.example.springsecurity7userrole.pojo.SysRole;

import java.util.List;

public interface RoleMapper {

    List<SysRole> selectRoleByUser(Integer userId);
}
