package com.ccnu.tour.service.Impl;

import com.ccnu.tour.dao.UserMapper;
import com.ccnu.tour.pojo.User;
import com.ccnu.tour.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: yang
 * @CreateTime: 2021-02-20 21:50
 * @Description: 用户信息操作
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public boolean insert(User user) {
        return userMapper.insert(user)>0;
    }

    @Override
    public boolean updatePasswordByPhone(String phone, String password) {
        return userMapper.updatePasswordByPhone(phone,password)>0;
    }
}
