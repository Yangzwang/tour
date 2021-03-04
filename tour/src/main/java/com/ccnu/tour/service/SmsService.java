package com.ccnu.tour.service;

public interface SmsService {

    void sendCode(String phone);

    boolean verificationCode(String phone, String code);

}
