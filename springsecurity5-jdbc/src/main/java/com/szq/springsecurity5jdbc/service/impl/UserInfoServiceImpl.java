package com.szq.springsecurity5jdbc.service.impl;

import com.szq.springsecurity5jdbc.dao.UserInfoDao;
import com.szq.springsecurity5jdbc.pojo.UserInfo;
import com.szq.springsecurity5jdbc.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUseName(String username) {
        return userInfoDao.findByUsername(username);
    }
}
