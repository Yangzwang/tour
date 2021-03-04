package com.ccnu.tour.config;

import ch.qos.logback.classic.util.EnvUtil;
import com.ccnu.tour.manager.UserAuthorizationManager;
import com.ccnu.tour.pojo.User;
import com.ccnu.tour.util.CommonUtil;
import com.ccnu.tour.util.ErrorEnum;
import com.ccnu.tour.util.RequestExtract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import static com.ccnu.tour.util.ErrorEnum.E_401;

/**
 * @Author: yang
 * @CreateTime: 2021-02-23 21:07
 * @Description: 用户权限校验
 */
public class UserAuthorizationFilter implements Filter {

    public static final Logger logger = LoggerFactory.getLogger(UserAuthorizationFilter.class);


    private UserAuthorizationManager userAuthorizationManager;

    public void init(FilterConfig config) throws ServletException {
        logger.info("init UserAuthorizationFilterV2");
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        userAuthorizationManager = springContext.getBean("userAuthorizationManager", UserAuthorizationManager.class);
        // logger.info("UserAuthorizationFilterV2 isTestEnv:{}", isTestEnv);

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest requestWrpper = (HttpServletRequest) request;
        try {

           /* if ("options".equalsIgnoreCase(requestWrpper.getMethod())){
                chain.doFilter(requestWrpper, response);
                return;
            }*/
            String token = RequestExtract.getToken(requestWrpper);
            logger.info("token:{}",token);
            if (token != null) {
                User user = userAuthorizationManager.validationUserLogin(token);
                if (user != null) {
                    requestWrpper.setAttribute("user_info",user);
                    chain.doFilter(requestWrpper, response);
                    return;
                }
            }
            httpResponse.setStatus(HttpStatus.OK.value());
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(CommonUtil.errorJson(E_401).toJSONString().getBytes());
            outputStream.close();

        } catch (Exception e) {
            httpResponse.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        }
    }

    public void destroy() {
    }
}
