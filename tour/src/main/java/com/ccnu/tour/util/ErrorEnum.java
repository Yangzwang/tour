package com.ccnu.tour.util;

public enum ErrorEnum {
    E_400(400, "页面错误"),
    E_500(500, "GET和POST请求有误"),
    E_90003(9003, "缺少必要参数");


    private Integer errorCode;
    private String errorMsg;


    private ErrorEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;

    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
