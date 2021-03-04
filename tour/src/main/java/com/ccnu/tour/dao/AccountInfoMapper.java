package com.ccnu.tour.dao;


import com.ccnu.tour.pojo.AccountInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountInfoMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(AccountInfo record);

    AccountInfo findById(Integer id);

    int updateByPrimaryKey(AccountInfo record);

    AccountInfo findByAccountType(Integer accountType);
}