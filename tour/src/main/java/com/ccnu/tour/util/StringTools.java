package com.ccnu.tour.util;

import java.util.UUID;

public class StringTools {

    public static Boolean isNullOrEmpty(Object s){
        if(s==null||s.toString().equals(""))
        return  true;
        return false;

    }
    /**
     * 生成token
     * @return
     */
    public static String GetGUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
