package com.ccnu.tour.controller;

import com.alibaba.fastjson.JSONObject;
import com.ccnu.tour.config.InterProcessLock;
import com.ccnu.tour.service.SmsService;
import com.ccnu.tour.util.CommonUtil;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.ccnu.tour.util.ErrorEnum.E_10005;

/**
 * @Author: yang
 * @CreateTime: 2021-03-03 22:52
 * @Description: 验证码相关
 */
@RestController
@RequestMapping("/api/pb/code")
public class VerificationCodeController {

    @Autowired
    private SmsService smsService;

    private static Logger log = LoggerFactory.getLogger(PbUserController.class);

    @RequestMapping(value = "/send_code", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject sendCode(@RequestBody JSONObject requestJson, HttpServletRequest request) {
        CommonUtil.hasAllRequired(requestJson, "phone");
        String phone = requestJson.getString("phone");
        InterProcessMutex interProcessMutex = InterProcessLock.initInterProcessMutex("/send_code/" + phone);
        try {
            interProcessMutex.acquire();
            smsService.sendCode(requestJson.getString("phone"));
            interProcessMutex.release();
        } catch (Exception e) {
            log.error("send code fail,phone:{}", requestJson.getString("phone"), e);
            return CommonUtil.errorJson(E_10005);
        }

        return CommonUtil.successJson("验证码发送成功");
    }

}
