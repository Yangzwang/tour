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
@RequestMapping("/api/pb/user")
public class PbLoginController {
    @Resource
    UserService userService;
    @Autowired
    RedisService redisService;

    private static Logger log = LoggerFactory.getLogger(PbLoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestBody User user, HttpServletRequest request) {
        //String token = request.getHeader("token");
        User user1 = userService.findByUserName(user.getLoginname());

        if (user1 == null) {
            log.error(ErrorEnum.E_10001.getErrorMsg()+"username:{}",user.getLoginname());
            throw new CommonJsonException(ErrorEnum.E_10001);
        }
        if(!user1.getPasswd().equals(user.getPasswd())){
            throw new CommonJsonException(ErrorEnum.E_10002);
        }
        String token=StringTools.GetGUID();
        redisService.set(token,user1.getLoginname());
        return CommonUtil.successJson(token);

    }
}
