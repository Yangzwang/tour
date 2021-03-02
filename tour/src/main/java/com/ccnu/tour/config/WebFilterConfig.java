package com.ccnu.tour.config;

import com.ccnu.tour.manager.UserAuthorizationManager;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
* Created by zhangdingshui on 2020/05/23
*/
@Configuration
public class WebFilterConfig {

    /*@Bean
    public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilterRegistrationBean() {
        FilterRegistrationBean<HiddenHttpMethodFilter> registrationBean = new FilterRegistrationBean<HiddenHttpMethodFilter>();
        registrationBean.setName("hiddenHttpMethodFilter");
        HiddenHttpMethodFilter greetingFilter = new HiddenHttpMethodFilter();
        registrationBean.setFilter(greetingFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(10);
        return registrationBean;
    }*/

   /* @Bean
    public FilterRegistrationBean serverAuthorizationFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("serverAuthorizationFilter");
        ServerAuthorizationFilter serverAuthorizationFilter = new ServerAuthorizationFilter();
        registrationBean.setFilter(serverAuthorizationFilter);
        registrationBean.addUrlPatterns("/api/pi/*","/api/pbff/*");
        registrationBean.setOrder(20);
        return registrationBean;
    }*/

   /* @Bean
    public FilterRegistrationBean h5XCrossAllowFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("h5XCrossAllowFilter");
        H5XCrossAllowFilter greetingFilter = new H5XCrossAllowFilter();
        registrationBean.setFilter(greetingFilter);
        registrationBean.addUrlPatterns("/api/h5pt/*");
        registrationBean.addUrlPatterns("/api/pb/*");
        registrationBean.addUrlPatterns("/api/cpb/*");
        registrationBean.setOrder(30);
        return registrationBean;
    }*/

    @Bean
    public FilterRegistrationBean userAuthorizationFilterRegistrationBean() {
        FilterRegistrationBean<UserAuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setName("userAuthorizationFilter");
        UserAuthorizationFilter greetingFilter = new UserAuthorizationFilter();
        registrationBean.setFilter(greetingFilter);
        registrationBean.addUrlPatterns("/api/pt/*");
        registrationBean.addUrlPatterns("/api/h5pt/*");
        registrationBean.setOrder(40);
        return registrationBean;
    }
    @Bean
    public UserAuthorizationManager userAuthorizationManager(){
        UserAuthorizationManager  userAuthorizationManager=new UserAuthorizationManager();
        return userAuthorizationManager;
    }

}
