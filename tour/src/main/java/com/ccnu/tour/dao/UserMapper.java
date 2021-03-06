package com.ccnu.tour.dao;


import com.ccnu.tour.pojo.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User user);

    User findById(String id);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);

    User findByUserName(@Param("username") String username);

   int  updatePasswordById(@Param("password") String password,@Param("id")Long id);

}