package com.atguigu.gmall0422.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall0422.bean.UserAddress;
import com.atguigu.gmall0422.bean.UserInfo;
import com.atguigu.gmall0422.service.UserInfoService;
import com.atguigu.gmall0422.user.mapper.UserAddressMapper;
import com.atguigu.gmall0422.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;

@Service
public class UserServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserInfo> getAllUser() {
        return userInfoMapper.selectAll();
    }

    @Override
    public List<UserInfo> getInfoByUserInfo(UserInfo userInfo) {
        return null;
    }

    @Override
    public List<UserInfo> getInfoByLoginName(String loginName) {
        return null;
    }

    @Override
    public void addUser(UserInfo userInfo) {

    }

    @Override
    public void updateUser(UserInfo userInfo) {

    }

    @Override
    public void deleteUser(UserInfo userInfo) {

    }

    @Override
    public List<UserAddress> findAddressByUserId(String userId) {
        UserAddress userAddress=new UserAddress();
        userAddress.setUserId(userId);
        return  userAddressMapper.select(userAddress);

    }
}
