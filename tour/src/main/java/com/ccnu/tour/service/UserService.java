package com.ccnu.tour.service;
import com.alibaba.fastjson.JSONObject;
import com.ccnu.tour.pojo.User;

public interface UserService {

   User findByUserName(String username);

   User findById(Integer id);

   boolean insert(User record);

   boolean updatePasswordByPhone(String phone,String password);

   boolean  updatePasswordById(Long id,String password);


}
