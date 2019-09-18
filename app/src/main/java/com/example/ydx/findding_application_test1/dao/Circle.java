package com.example.ydx.findding_application_test1.dao;

/**
 * Created by ghjhh on 2018/3/4.
 */

import android.util.Log;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;

import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * 生成圆的相关方法
 */
public class Circle {
    private BaiduMap mBaiduMap;
    /**
     * 从数据库读取半径
     */
    public void getRadius(){

    }
    /**参数：第几次缩圈
     * 生成圈半径的方法
     * 返回：生成圈的半径
     */
    public int creatRadius(int frequency){
        int radius = 0;
        switch (frequency){
            case 1:
                radius = 1000;
                break;
            case 2:
                radius = 500;
                break;
            case 3:
                radius = 300;
                break;
            case 4:
                radius = 100;
                break;
            case 5:
                radius = 50;
                break;
            case 6:
                radius = 10;
                break;
        }
        return radius;
    }
    /**参数：之前圆的圆心位置和半径与本次圆的半径
     * 生成圆心的方法
     * 返回：本次生成的圆心位置
     */
    public String creatCenter(String centerOfCircle,int r,int radius){
        String center = null;
        double a = 0,b = 0;
        int r1= r - radius;
        Double random = Math.random();
        int r2 = r1*Integer.parseInt(new java.text.DecimalFormat("0").format(random));
        String[] jingWei = centerOfCircle.split(",");
        String jing = jingWei[0];
        String wei = jingWei[1];
        //随机方向 0 - 360度
        double CurrentAngle = Math.random() * 361;
        float pi = 3.1415926f;
        String out = String.valueOf(CurrentAngle);
        a = Math.sin(  CurrentAngle * pi / 180  ) * r2/100000+Double.parseDouble(jing);
        b = Math.cos(  CurrentAngle * pi / 180)/100000/1.1 * r2+Double.parseDouble(wei);
        center = a+","+b;
        return center;
    }
    /**
     * 存储圆半径的方法
     */
    public void saveRadius(){

    }
    /**
     * 画圆的方法
     */
    public void createCircle(Double lat,Double lon,int r,BaiduMap mBaiduMap){
        LatLng llCircle = new LatLng(lat, lon);
        OverlayOptions ooCircle = new CircleOptions().fillColor(0x000000FF)
                .center(llCircle).stroke(new Stroke(5, 0xAA000000))
        .radius(r);
        Log.e("hah",lat+",");
        mBaiduMap.addOverlay(ooCircle);
    }
}
