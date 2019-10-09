package com.atguigu.gmall0422.service;



import com.atguigu.gmall0422.bean.UserAddress;
import com.atguigu.gmall0422.bean.UserInfo;

import java.util.List;

public interface UserInfoService {
    //查询所有用户
    List<UserInfo> getAllUser();
    //根据用户输入的条件查询
    List<UserInfo> getInfoByUserInfo(UserInfo userInfo);
    //模糊查询
    List<UserInfo> getInfoByLoginName(String loginName);
    //添加
    void addUser(UserInfo userInfo);
    //修改
    void updateUser(UserInfo userInfo);
    //删除
    void deleteUser(UserInfo userInfo);
    //根据用户id查询地址
    List<UserAddress>findAddressByUserId(String userId);
}
