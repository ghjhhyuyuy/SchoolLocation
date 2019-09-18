package com.example.ydx.findding_application_test1;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLng;
import com.example.ydx.findding_application_test1.dao.Circle;
import com.example.ydx.findding_application_test1.dao.location;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

/**
 * Created by ghjhh on 2018/3/5.
 */

public class PlayGame {
    private int recLen = 11;
    int r =0;
    /**
     * 运行游戏时的管理代码
     */
    public void game(Double lat,Double lon,BaiduMap mBaiduMap){
        /**
         * 画圈代码
         */
        int r =0;
        int frequency = 1;
        Circle circle = new Circle();
        int R =circle.creatRadius(frequency);
        for(;frequency<=6;frequency++){
            circle.createCircle(lat,lon,R,mBaiduMap);
            String center = lon+","+lat;
            r = R;
            R = circle.creatRadius(frequency);
            String res = circle.creatCenter(center,R,r);
            String all[] = res.split(",");
            lon = Double.parseDouble(all[0]);
            lat = Double.parseDouble(all[1]);

        }
        /**
         * 计时代码
         */
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//        executor.scheduleWithFixedDelay(
//                new MyRunnable(lat,lon,mBaiduMap),
//                0,
//                1000,
//                TimeUnit.MILLISECONDS);
/**
 * 定位代码
 */
//        location loc = new location();
//        String[] haha = new String[5];
//        haha[0]="116.391621,39.936183";
//        haha[1]="116.357126,39.954326";
//        haha[2]="116.450837,39.988388";
//        haha[3]="116.439339,39.885709";
//        haha[4]="116.276638,39.850709";
//        loc.tracingPoint(haha, mBaiduMap);
    }

}
class MyRunnable implements Runnable {
    Double lon,lat;
    BaiduMap mBaiduMap;
    int r =0;
    int frequency = 1;
    Circle circle = new Circle();
    int R =circle.creatRadius(frequency);
    public MyRunnable(Double lon, Double lat, BaiduMap mBaiduMap) {
        this.lon = lon;
        this.lat = lat;
        this.mBaiduMap = mBaiduMap;
    }

    @Override
    public void run() {
        circle.createCircle(lat,lon,R,mBaiduMap);
        String center = lon+","+lat;
        r = R;
        R = circle.creatRadius(frequency);
        String res = circle.creatCenter(center,R,r);
        String all[] = res.split(",");
        lon = Double.parseDouble(all[0]);
        lat = Double.parseDouble(all[1]);
        frequency++;
        if(frequency>6){
            return;
        }
    }
}
