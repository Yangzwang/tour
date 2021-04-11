package com.ccnu.tour.controller;

import com.alibaba.fastjson.JSONObject;
import com.ccnu.tour.config.CommonJsonException;
import com.ccnu.tour.config.InterProcessLock;
import com.ccnu.tour.pojo.User;
import com.ccnu.tour.service.RedisService;
import com.ccnu.tour.service.SmsService;
import com.ccnu.tour.service.UserService;
import com.ccnu.tour.util.AESUtil;
import com.ccnu.tour.util.CommonUtil;
import com.ccnu.tour.util.ErrorEnum;
import com.ccnu.tour.util.StringTools;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/pi/user")
public class PiUserController {
    @Resource
    UserService userService;
    @Autowired
    RedisService redisService;
    @Autowired
    SmsService smsService;
    @Value("${img.url}")
    private String imgUrl;

    private static Logger log = LoggerFactory.getLogger(PiUserController.class);

    private static final String TOKEN_BASE_KSY = "token:";


    private static final long seconds = 60 * 60 * 24 * 7;

    @RequestMapping(value = "/user_info_by_ids", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        CommonUtil.hasAllRequired(requestJson, "user_ids");
        String userString = requestJson.getString("user_ids");
        List<Long> userIds=JSONObject.parseArray(userString,Long.class);
        List<User> userList = userService.findByIds(userIds);
        return CommonUtil.successJson(userList);

    }

}
