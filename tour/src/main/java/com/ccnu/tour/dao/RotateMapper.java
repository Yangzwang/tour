package com.ccnu.tour.dao;


import com.ccnu.tour.pojo.Rotate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RotateMapper {
    List<Rotate> finByCityId(Integer id);

}