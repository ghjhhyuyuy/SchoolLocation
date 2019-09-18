package com.example.ydx.findding_application_test1;

import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ghjhh on 2018/1/1.
 */

public class LineUtils {
    public OverlayOptions DrawLine(){
        //构建折线点坐标
        LatLng p1 = new LatLng(29.982393, 103.005045);
        LatLng p2 = new LatLng(29.982268, 103.004919);
        LatLng p3 = new LatLng(29.981549, 103.004758);
        LatLng p4 = new LatLng(29.979922, 103.00332);
        LatLng p5 = new LatLng(29.978577, 103.000931);
        LatLng p6 = new LatLng(29.978452, 103.000176);
        LatLng p7 = new LatLng(29.979797, 102.997391);
        LatLng p8 = new LatLng(29.980125, 102.99714);
        LatLng p9 = new LatLng(29.980712, 102.997068);
        LatLng p10 = new LatLng(29.982002, 102.997364);
        LatLng p11 = new LatLng(29.982659, 102.995792);
        LatLng p12 = new LatLng(29.983355, 102.995397);
        LatLng p13 = new LatLng(29.984356, 102.993385);
        LatLng p14 = new LatLng(29.984763, 102.993349);
        LatLng p15 = new LatLng(29.98492, 102.994211);
        LatLng p16 = new LatLng(29.985326, 102.994193);
        LatLng p17 = new LatLng(29.985889, 102.992496);
        LatLng p18 = new LatLng(29.988689, 102.992585);
        LatLng p19 = new LatLng(29.988908, 102.993268);
        LatLng p20 = new LatLng(29.987672, 102.994921);
        LatLng p21 = new LatLng(29.987133, 102.995478);
        LatLng p22 = new LatLng(29.986937, 102.996619);
        LatLng p23 = new LatLng(29.986272, 102.997517);
        LatLng p24 = new LatLng(29.985616, 102.997823);
        LatLng p25 = new LatLng(29.985428, 102.998397);
        LatLng p26 = new LatLng(29.985248, 102.99997);
        LatLng p27 = new LatLng(29.985154, 103.00111);
        LatLng p28 = new LatLng(29.984826, 103.00226);
        LatLng p29 = new LatLng(29.983269, 103.00403);
        LatLng p30 = new LatLng(29.982448, 103.004919);
        LatLng p31 = new LatLng(29.982393, 103.005045);

        LatLng A1 = new LatLng(29.982714, 103.005359);
        LatLng A2 = new LatLng(29.982143, 103.00624);
        LatLng A3 = new LatLng(29.983207, 103.006931);
        LatLng A4 = new LatLng(29.983379, 103.007425);
        LatLng A5 = new LatLng(29.983222, 103.00809);
        LatLng A6 = new LatLng(29.983332, 103.00995);
        LatLng A7 = new LatLng(29.98352, 103.010165);
        LatLng A8 = new LatLng(29.985397, 103.010525);
        LatLng A9 = new LatLng(29.986491, 103.010588);
        LatLng A10 = new LatLng(29.987899, 103.010598);
        LatLng A11 = new LatLng(29.988118, 103.009195);
        LatLng A12 = new LatLng(29.986147, 103.007686);
        LatLng A13 = new LatLng(29.986531, 103.006842);
        LatLng A14 = new LatLng(29.984615, 103.00553);
        LatLng A15 = new LatLng(29.984177, 103.004461);
        LatLng A16 = new LatLng(29.983582, 103.004668);
        LatLng A17 = new LatLng(29.982925, 103.005467);
        LatLng A18 = new LatLng(29.982714, 103.005359);
        List<LatLng> points = new ArrayList<LatLng>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        points.add(p7);
        points.add(p8);
        points.add(p9);
        points.add(p10);
        points.add(p11);
        points.add(p12);
        points.add(p13);
        points.add(p14);
        points.add(p15);
        points.add(p16);
        points.add(p17);
        points.add(p18);
        points.add(p19);
        points.add(p20);
        points.add(p21);
        points.add(p22);
        points.add(p23);
        points.add(p24);
        points.add(p25);
        points.add(p26);
        points.add(p27);
        points.add(p28);
        points.add(p29);
        points.add(p30);
        points.add(p31);
        points.add(A1);
        points.add(A2);
        points.add(A3);
        points.add(A4);
        points.add(A5);
        points.add(A6);
        points.add(A7);
        points.add(A8);
        points.add(A9);
        points.add(A10);
        points.add(A11);
        points.add(A12);
        points.add(A13);
        points.add(A14);
        points.add(A15);
        points.add(A16);
        points.add(A17);
        points.add(A18);
        //绘制折线
        OverlayOptions ooPolyline = new PolylineOptions().width(10)
                .color(0xAAFF0000).points(points);
        return ooPolyline;
    }
}
