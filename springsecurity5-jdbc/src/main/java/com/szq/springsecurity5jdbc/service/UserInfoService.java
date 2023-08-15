package com.szq.springsecurity5jdbc.service;

import com.szq.springsecurity5jdbc.pojo.UserInfo;

public interface UserInfoService {

    UserInfo findByUseName(String username);
}
