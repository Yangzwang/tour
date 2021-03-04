package com.ccnu.tour.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ccnu.tour.config.CommonJsonException;
import com.ccnu.tour.dao.AccountInfoMapper;
import com.ccnu.tour.pojo.AccountInfo;
import com.ccnu.tour.service.RedisService;
import com.ccnu.tour.service.SmsService;
import com.ccnu.tour.util.AESUtil;
import com.ccnu.tour.util.ErrorEnum;
import com.ccnu.tour.util.HttpUtil;
import com.ccnu.tour.util.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yang
 * @CreateTime: 2021-03-02 21:52
 * @Description: 短信相关
 */
@Service
public class SmsServiceImpl implements SmsService {
    private static Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);
    private static final String SMS_URL = "http://yzx.market.alicloudapi.com/yzx/sendSms";

    private static final  long  seconds=30*60;

    private static final String CODE_BASE_KSY = "code:";
    @Autowired
    private AccountInfoMapper accountInfoMapper;
    @Autowired
    private RedisService redisService;

    public void sendCode(String phone) {
        AccountInfo accountInfo = accountInfoMapper.findByAccountType(1);
        String appCode = AESUtil.doDecrypt(accountInfo.getSecretKey());
        String templateId = accountInfo.getRemarks();
        String code = StringTools.getRandom();
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + appCode);
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("mobile", phone);
        queryMap.put("param", "code:" + code);
        queryMap.put("tpl_id", templateId);
        log.info("send code ,phone:{}, errorInfo:{}", phone, JSONObject.toJSONString(queryMap));

        //调三方接口发起短信
        String json = HttpUtil.post(SMS_URL, headers, queryMap);
        JSONObject jsonObject = JSON.parseObject(json);
        if ("00000".equals(jsonObject.getString("return_code"))) {
            //发送短信成功，写入缓存
            redisService.set(CODE_BASE_KSY + phone, code);
            redisService.expire(CODE_BASE_KSY + phone,seconds);
        } else {
            log.error("send code fail ,phone:{}, errorInfo:{}", phone, json);
            throw new CommonJsonException(ErrorEnum.E_10005);
        }
    }

    @Override
    public boolean verificationCode(String phone, String code) {
        String old_code = redisService.get(CODE_BASE_KSY + phone);
        if (code.equals(old_code)) {
            return true;
        }
        return false;
    }


}
