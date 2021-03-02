package com.ccnu.tour.manager;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.ccnu.tour.pojo.User;
import com.ccnu.tour.service.RedisService;
import com.ccnu.tour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: yang
 * @CreateTime: 2021-02-23 21:18
 * @Description: 用户权限处理
 */
public class UserAuthorizationManager {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;


    public User validationUserLogin(String token) {
        String userJson = redisService.get(token);
        if (StringUtils.isEmpty(userJson)) {
            return null;
        }
        return userService.findByUserName(userJson);

    }


}
