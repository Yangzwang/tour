package com.ccnu.tour.util;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class StringTools {

    private static final String[] nikeNames = {"美国队长", "钢铁侠", "雷神", "绿巨人", "黑寡妇", "鹰眼", "死侍", "毒液", "神奇女侠", "奇异博士"};
    private static final String[] photoUrls = {"/img/nian1513845632127.png", "/img/nian1513845632127.png",
            "/img/nian1513845632127.png", "/img/nian1513845632127.png",
            "/img/nian1513845632127.png", "/img/nian1513845632127.png",
            "/img/nian1513845632127.png", "/img/nian1513845632127.png", "/img/nian1513845632127.png",
            "/img/nian1513845632127.png"};

    public static Boolean isNullOrEmpty(Object s) {
        if (s == null || s.toString().equals(""))
            return true;
        return false;

    }

    /**
     * 生成token
     *
     * @return
     */
    public static String GetGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public static String getRandom() {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int r = random.nextInt(10); //每次随机出一个数字（0-9）
            code = code + r;  //把每次随机出的数字拼在一起
        }
        return code;
    }

    /**
     * 随机获取一个昵称
     *
     * @return
     */
    public static String getNikeName() {
        Random random = new Random();
        int r = random.nextInt(10); //每次随机出一个数字（0-9）
        return nikeNames[r];
    }

    /**
     * 随机获取一个头像
     *
     * @return
     */
    public static String photoUrl() {
        Random random = new Random();
        int r = random.nextInt(10); //每次随机出一个数字（0-9）
        return photoUrls[r];
    }


}
