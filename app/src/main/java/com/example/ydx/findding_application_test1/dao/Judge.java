package com.example.ydx.findding_application_test1.dao;

/**
 * Created by ghjhh on 2018/3/4.
 */

/**
 * 判断是否在圈内的方法
 */
public class Judge {
    public boolean judge(String location1,String center,int r){
        double part1=0;
        double res = 0;
        String[] loc1 = location1.split(",");
        String[] loc2 = center.split(",");
        part1 = Math.sin(Double.parseDouble(loc1[1]))*Math.sin(Double.parseDouble(loc2[1]))+Math.cos(Double.parseDouble(loc1[1]))*Math.cos(Double.parseDouble(loc2[1]))*Math.cos(Double.parseDouble(loc1[0])-Double.parseDouble(loc2[0]));
        res = 111.12*Math.cos(1/part1);
        if(res<=r){
            return true;
        }
        else {
            return false;
        }
    }
}
