package com.ccnu.tour.controller;

import com.alibaba.fastjson.JSONObject;
import com.ccnu.tour.config.CommonJsonException;
import com.ccnu.tour.pojo.User;
import com.ccnu.tour.service.RedisService;
import com.ccnu.tour.service.UserService;
import com.ccnu.tour.util.CommonUtil;
import com.ccnu.tour.util.ErrorEnum;
import com.ccnu.tour.util.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/pt/user")
public class PtUserController {
    @Resource
    UserService userService;
    @Autowired
    RedisService redisService;

    private static Logger log = LoggerFactory.getLogger(PtUserController.class);

    @RequestMapping(value = "/user_info", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(HttpServletRequest request) {
        //String token = request.getHeader("token");
        User user= (User) request.getAttribute("user_info");
        return CommonUtil.successJson(user);

    }
}
