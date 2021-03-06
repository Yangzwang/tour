package com.ccnu.tour.dao;


import com.ccnu.tour.pojo.TourBean;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface TourMapper {

   Page<TourBean> getQueryTour();
}
