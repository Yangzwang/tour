package com.ccnu.tour.service;
import com.alibaba.fastjson.JSONObject;
import com.ccnu.tour.pojo.TourBean;

import java.util.List;

public interface TourService {

   JSONObject getQueryTour(Integer pageNum, Integer pageSize);
}
