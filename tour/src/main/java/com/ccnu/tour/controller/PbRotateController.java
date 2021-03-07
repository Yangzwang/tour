package com.ccnu.tour.controller;

import com.alibaba.fastjson.JSONObject;
import com.ccnu.tour.pojo.Rotate;
import com.ccnu.tour.service.RotateService;
import com.ccnu.tour.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: yang
 * @CreateTime: 2021-03-07 22:56
 * @Description: 轮播图
 */
@RestController
@RequestMapping("/api/pb/rotate")
public class PbRotateController {
    @Autowired
    private RotateService rotateService;

    @RequestMapping(value = "/get_list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getList(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        CommonUtil.hasAllRequired(requestJson, "city_id");
        List<Rotate> rotates = rotateService.finByCityId(requestJson.getIntValue("city_id"));
        return CommonUtil.successJson(rotates);
    }

}
