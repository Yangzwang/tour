package com.ccnu.tour.service;
import com.alibaba.fastjson.JSONObject;

public interface TourService {

   JSONObject getQueryTour(Integer pageNum, Integer pageSize);
}
