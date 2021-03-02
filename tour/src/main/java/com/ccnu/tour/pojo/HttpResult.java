package com.ccnu.tour.pojo;

import com.ccnu.tour.util.CommonUtil;

/**
 * Created by sh-lx on 2017/5/31.
 */

public class HttpResult<T> {

    //  判断标示
    private Integer returnCode;

    private String returnMsg;

    private T data;



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}
