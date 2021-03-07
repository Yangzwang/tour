package com.ccnu.tour.service.Impl;

import com.ccnu.tour.dao.RotateMapper;
import com.ccnu.tour.pojo.Rotate;
import com.ccnu.tour.service.RotateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yang
 * @CreateTime: 2021-03-07 22:54
 * @Description: 轮播图
 */
@Service
public class RotateServiceImpl implements RotateService {
    @Autowired
    private RotateMapper mapper;
    @Override
    public List<Rotate> finByCityId(Integer id) {
        return mapper.finByCityId(id);
    }
}
