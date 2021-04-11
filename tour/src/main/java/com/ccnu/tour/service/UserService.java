package com.ccnu.tour.service;
import com.alibaba.fastjson.JSONObject;
import com.ccnu.tour.pojo.User;

import java.util.Collection;
import java.util.List;

public interface UserService {

   User findByUserName(String username);

   User findById(Long id);

   boolean insert(User record);

   boolean updatePasswordByPhone(String phone,String password);

   boolean  updatePasswordById(Long id,String password);

   boolean updateByPrimaryKeySelective(User user);

   List<User> findByIds(Collection<Long> ids);


}
