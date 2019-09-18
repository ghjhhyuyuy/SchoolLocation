package com.example.ydx.findding_application_test1.dao;

/**
 * Created by ghjhh on 2018/3/4.
 */

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.ydx.findding_application_test1.R;

/**
 * 获得位置的相关方法
 */
public class location {
    /**
     * 从数据库获取队伍成员位置
     */
    public String[] getLocation(){
        String[] location = new String[5];
        return  location;
    }
    /**
     * 用得到的队伍成员位置在地图上进行描点
     */
    public void tracingPoint(String[] location,BaiduMap mBaiduMap){
        for(int i = 0;i<location.length;i++){
            String[] crrect = location[i].split(",");
            Double lat = Double.parseDouble(crrect[1]);
            Double lon = Double.parseDouble(crrect[0]);
            LatLng point = new LatLng(lat, lon);

//构建Marker图标

            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_marka);

//构建MarkerOption，用于在地图上添加Marker

            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);

//在地图上添加Marker，并显示

            mBaiduMap.addOverlay(option);
        }
    }

}
