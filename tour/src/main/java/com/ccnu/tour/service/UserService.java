package com.ccnu.tour.service;
import com.alibaba.fastjson.JSONObject;
import com.ccnu.tour.pojo.User;

public interface UserService {

   User findByUserName(String username);

   User findById(String id);


}
