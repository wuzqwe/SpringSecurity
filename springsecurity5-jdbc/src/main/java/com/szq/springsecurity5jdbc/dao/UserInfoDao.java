package com.szq.springsecurity5jdbc.dao;

import com.szq.springsecurity5jdbc.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo,Long> {
    UserInfo findByUsername(String username);
}
