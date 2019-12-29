package com.ccnu.tour.dao;

import com.ccnu.tour.pojo.TourBean;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TourDao {

   Page<TourBean> getQueryTour();
}
