package com.ccnu.tour.controller;

import com.ccnu.tour.pojo.Person;
import com.ccnu.tour.pojo.Son;
import com.ccnu.tour.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;

public class Main {
    @Autowired
   static TourService  tourService;
    public static void main(String[] args) {

         int a=10;
         System.out.println(a);

        Person person=new Son();
        person.setA(1);
        Son son=(Son)person;
        son.setB(5);

        System.out.print(son.getB());

    }
}
