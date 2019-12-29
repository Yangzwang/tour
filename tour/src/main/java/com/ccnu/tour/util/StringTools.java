package com.ccnu.tour.util;

public class StringTools {

    public static Boolean isNullOrEmpty(Object s){
        if(s==null||s.toString().equals(""))
        return  true;
        return false;

    }
}
