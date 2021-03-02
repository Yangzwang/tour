package com.ccnu.tour.util;

import com.alibaba.druid.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yang
 * @CreateTime: 2021-02-23 21:52
 * @Description: 请求参数
 */
public class RequestExtract {

    private static final String KEY_AUTH_TOKEN="token";

    /**
     * 获取用户加密信息
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getParameter(KEY_AUTH_TOKEN);
        if (StringUtils.isEmpty(token)) {
            token = request.getHeader(KEY_AUTH_TOKEN);
        }
        return token;
    }

}
