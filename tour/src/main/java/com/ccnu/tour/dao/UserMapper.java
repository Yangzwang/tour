package com.ccnu.tour.dao;


import com.ccnu.tour.pojo.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


    int insert(User record);



    int updateByPrimaryKeySelective(User user);


    User findByUserName(@Param("username") String username);

    int updatePasswordByPhone(@Param("phone")String phone,@Param("password")String password);

   int  updatePasswordById(@Param("password") String password,@Param("id")Long id);

}