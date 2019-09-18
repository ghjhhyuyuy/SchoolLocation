package com.example.ydx.findding_application_test1;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.tts.client.SpeechSynthesizer;
import com.example.ydx.findding_application_test1.dao.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private Button startButton = null;
    private SpeechSynthesizer mSpeechSynthesizer;
    private Polyline mPolyline;
    private Button bt1;
    private boolean flag = false;
    private SQLiteDatabase database;
    private LocationClient locationClient = null;
    // 定位
    public LocationClient mLocationClient = null;
    //public BDLocationListener myListener = new MyLocationListener(); // 位置监听器
    public BDLocationListener myListener; // 位置监听器
    private Button bt;
    private Button bt2;
    private Button bt3;
    private static int LOCATION_COUTNS = 0;
    private TextView locationInfoTextView = null;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    private String address;
    GeoCoder mSearch;
    private TextView conaddress;
    LatLng latlng;
    private Button ok;
    double lon,lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // database = new DBAdapter(getApplicationContext()).getReadableDatabase();
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        // 获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);

        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        init();
        Toast.makeText(getApplicationContext(),"点击地图选择圆心，然后开始游戏", Toast.LENGTH_LONG).show();
        bt2 = (Button)findViewById(R.id.btn_test) ;
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testLocation(v);
            }
        });
        bt =(Button) findViewById(R.id.btn_mylocation);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationClientOption option = new LocationClientOption();
                option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
                option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
                //int span=5000;
                // option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
                option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
                option.setOpenGps(true);//可选，默认false,设置是否使用gps
                option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
                option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
                option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
                option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
                option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
                option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
                mLocationClient.setLocOption(option);
                mLocationClient.start();
                mLocationClient.requestLocation();

                mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING,true,null,0,0));
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(16).build()));

                initLocation();
            }
        });
        bt1 =(Button) findViewById(R.id.btn_closeLable);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    mBaiduMap.hideInfoWindow();
                }
            }
        });

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            /**
             * 地图单击事件回调函数
             * @param point 点击的地理坐标
             */
            public void onMapClick(LatLng point){
                lat = point.latitude;
                lon = point.longitude;
                popWin(point);
                bt2 = (Button) findViewById(R.id.game) ;
                bt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PlayGame play = new PlayGame();
                        play.game(lat,lon,mBaiduMap);

                    }
                });
            }
            /**
             * 地图内 Poi 单击事件回调函数
             * @param
             */
            @Override
            public boolean onMapPoiClick(MapPoi arg0) {
                arg0.getName(); //名称
                arg0.getPosition(); //坐标
                Log.e(TAG, "123");
                Log.e(TAG, arg0.getName());
                return false;
            }
        });


        // 定位
        myListener = new MyLocationListener();
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );//注册监听函数
        locate();
        addOver();
        //画线
        LineUtils lineUtils = new LineUtils();
        OverlayOptions ooPolyline = lineUtils.DrawLine();
        mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
    }
    public void popWin(LatLng point) {
// 构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);
// 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions().position(point)
                .icon(bitmap);
// 在地图上添加Marker，并显示
        mBaiduMap.clear();
        mBaiduMap.addOverlay(option);
    }
    public void testLocation(View view)
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else
        {

        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

            } else
            {
                // Permission Denied
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=5000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
        mLocationClient.requestLocation();
        // 发送请求
    }

    public void mylocationClick(View view){
        initLocation();
    }


    public void onGetPoiDetailResult(PoiDetailResult result){
        //获取Place详情页检索结果
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mLocationClient.stop();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }


    /**
     * 位置监听器
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) { // 接收位置信息的回调方法
            //Receive Location

            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }

//            final ContentValues values = new ContentValues();
// values.put("latitude", location.getLatitude());//左字符串为表的属性，右字符串为对应的值
// values.put("lontitude", location.getLongitude());
//            database.insert("location", null, values);
            Log.i("BaiduLocationApiDemo", sb.toString());
//            Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();             `
            // 在地图上显示结果
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_st); // 图标
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            OverlayOptions options = new MarkerOptions()
                    .position(latLng).icon(bitmap); // 创建图层

            mBaiduMap.addOverlay(options);

            mBaiduMap.setMyLocationEnabled(true);
            MyLocationData locationData=new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(100)
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(locationData);

            mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,true,null,0,0));
        }

    }
    private void  addOver(){
        //定义Ground的显示地理范围
        LatLng southwest = new LatLng(29.982601,102.997966);
        LatLng northeast = new LatLng(29.983236,102.998741);
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(northeast)
                .include(southwest)
                .build();

//定义Ground显示的图片
        BitmapDescriptor bdGround = BitmapDescriptorFactory
                .fromResource(R.drawable.ground_overlay);

//定义Ground覆盖物选项
        OverlayOptions ooGround = new GroundOverlayOptions()
                .positionFromBounds(bounds)
                .image(bdGround)
                .transparency(0.8f);

//在地图中添加Ground覆盖物
        mBaiduMap.addOverlay(ooGround);
    }
    private void locate() {
        final LatLng point = new LatLng(29.982944, 103.000043);
//构建Marker图标
        final LatLng point1 = new LatLng(29.983026, 102.99826);
        final LatLng point2 = new LatLng(29.983941,103.006933);
        final LatLng point3 = new LatLng(29.984031,103.008712);
        final LatLng point4 = new LatLng(29.986491,103.009206);
        final LatLng point5 = new LatLng(29.985869,103.010176);
        final LatLng point6 = new LatLng(29.985102,102.997811);
        final LatLng point7 = new LatLng(29.98511,102.999428);
        final LatLng point8 = new LatLng(29.985087,102.999572);
        final LatLng point9 = new LatLng(29.983358,103.003048);
        final LatLng point10 = new LatLng(29.983366,103.003461);
        final LatLng point11 = new LatLng(29.984234,103.004153);
        final LatLng point12 = new LatLng(29.985576,103.006022);
        addMarker(point);
        addMarker(point1);
        addMarker(point2);
        addMarker(point3);
        addMarker(point4);
        addMarker(point5);
        addMarker(point6);
        addMarker(point7);
        addMarker1(point8);
        addMarker1(point9);
        addMarker1(point10);
        addMarker1(point11);
        addMarker1(point12);
//构建MarkerOption，用于在地图上添加Marker
//添加infoWindow
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                flag=true;
                if(marker.getPosition()==point ){
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    //定义用于显示该InfoWindow的坐标点
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.activity_edit, null);
                    Button txtLatLng = (Button) view.findViewById(R.id.text_item_latlng);
                    LatLng pt = new LatLng(29.982944, 103.000043);
                    //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                    InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
                    String text = txtLatLng.getText().toString();
                    //显示InfoWindow
                    mBaiduMap.showInfoWindow(mInfoWindow);
                    speak(0,text);//读十教

                }else if(marker.getPosition()==point1){
//                    if(flag1){
//                        mBaiduMap.hideInfoWindow();
//                        flag1 = false;
                    // }else{
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    //定义用于显示该InfoWindow的坐标点
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.activity_edit1, null);
                    View view1 = inflater.inflate(R.layout.activity_speak, null);
                    Button txtLatLng = (Button) view1.findViewById(R.id.text_item_latlng);
                    LatLng pt = new LatLng(29.983026, 102.99826);
                    //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                    InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
                    String text = txtLatLng.getText().toString();
                    //显示InfoWindow
                    mBaiduMap.showInfoWindow(mInfoWindow);
                    speak(0,text);//读图书馆
                    //flag1 = true;
                    // }
                }else if(marker.getPosition()==point2){
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    //定义用于显示该InfoWindow的坐标点
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.activity_edit2, null);

                    Button txtLatLng = (Button) view.findViewById(R.id.text_item_latlng1);
                    LatLng pt = new LatLng(29.983941,103.006933);
                    //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                    InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
                    String text = txtLatLng.getText().toString();
                    //显示InfoWindow
                    mBaiduMap.showInfoWindow(mInfoWindow);
                    speak(0,text);
                }else if(marker.getPosition()==point3){
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    //定义用于显示该InfoWindow的坐标点
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.activity_edit3, null);
                    Button txtLatLng = (Button) view.findViewById(R.id.text_item_latlng1);
                    LatLng pt = new LatLng(29.984031,103.008712);
                    //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                    InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
                    String text = txtLatLng.getText().toString();
                    //显示InfoWindow
                    mBaiduMap.showInfoWindow(mInfoWindow);
                    speak(0,text);
                }else if(marker.getPosition()==point4){
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    //定义用于显示该InfoWindow的坐标点
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.activity_edit4, null);
                    Button txtLatLng = (Button) view.findViewById(R.id.text_item_latlng1);
                    LatLng pt = new LatLng(29.986491,103.009206);
                    //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                    InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
                    String text = txtLatLng.getText().toString();
                    //显示InfoWindow
                    mBaiduMap.showInfoWindow(mInfoWindow);
                    speak(0,text);
                }else if(marker.getPosition()==point5){
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    //定义用于显示该InfoWindow的坐标点
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.activity_edit5, null);
                    Button txtLatLng = (Button) view.findViewById(R.id.text_item_latlng1);
                    LatLng pt = new LatLng(29.985869,103.010176);
                    //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                    InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
                    String text = txtLatLng.getText().toString();
                    //显示InfoWindow
                    mBaiduMap.showInfoWindow(mInfoWindow);
                    speak(0,text);
                }else if(marker.getPosition()==point6){
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    //定义用于显示该InfoWindow的坐标点
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.activity_edit6, null);
                    Button txtLatLng = (Button) view.findViewById(R.id.text_item_latlng1);
                    LatLng pt = new LatLng(29.985102,102.997811);
                    //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                    InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
                    String text = txtLatLng.getText().toString();
                    //显示InfoWindow
                    mBaiduMap.showInfoWindow(mInfoWindow);
                    speak(0,text);
                }else if(marker.getPosition()==point7){
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    //定义用于显示该InfoWindow的坐标点
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.activity_edit7, null);
                    Button txtLatLng = (Button) view.findViewById(R.id.text_item_latlng1);
                    LatLng pt = new LatLng(29.98511,102.999428);
                    //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                    InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
                    String text = txtLatLng.getText().toString();
                    //显示InfoWindow
                    mBaiduMap.showInfoWindow(mInfoWindow);
                    speak(0,text);
                }else if(marker.getPosition()==point8){
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    //定义用于显示该InfoWindow的坐标点
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.activity_edit8, null);
                    Button txtLatLng = (Button) view.findViewById(R.id.text_item_latlng1);
                    LatLng pt = new LatLng(29.985087,102.999572);
                    //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                    InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
                    String text = txtLatLng.getText().toString();
                    //显示InfoWindow
                    mBaiduMap.showInfoWindow(mInfoWindow);
                    speak(0,text);
                }else if(marker.getPosition()==point9){
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    //定义用于显示该InfoWindow的坐标点
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.activity_edit9, null);
                    Button txtLatLng = (Button) view.findViewById(R.id.text_item_latlng1);
                    LatLng pt = new LatLng(29.983358,103.003048);
                    //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                    InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
                    String text = txtLatLng.getText().toString();
                    //显示InfoWindow
                    mBaiduMap.showInfoWindow(mInfoWindow);
                    speak(0,text);
                }else if(marker.getPosition()==point10){
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    //定义用于显示该InfoWindow的坐标点
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.activity_edit10, null);
                    Button txtLatLng = (Button) view.findViewById(R.id.text_item_latlng1);
                    LatLng pt = new LatLng(29.983366,103.003461);
                    //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                    InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
                    String text = txtLatLng.getText().toString();
                    //显示InfoWindow
                    mBaiduMap.showInfoWindow(mInfoWindow);
                    speak(0,text);
                }else if(marker.getPosition()==point11){
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    //定义用于显示该InfoWindow的坐标点
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.activity_edit11, null);
                    Button txtLatLng = (Button) view.findViewById(R.id.text_item_latlng1);
                    LatLng pt = new LatLng(29.984234,103.004153);
                    //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                    InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
                    String text = txtLatLng.getText().toString();
                    //显示InfoWindow
                    mBaiduMap.showInfoWindow(mInfoWindow);
                    speak(0,text);
                }else if(marker.getPosition()==point12){
                    Button button = new Button(getApplicationContext());
                    button.setBackgroundResource(R.drawable.popup);
                    //定义用于显示该InfoWindow的坐标点
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.activity_edit12, null);
                    Button txtLatLng = (Button) view.findViewById(R.id.text_item_latlng1);
                    LatLng pt = new LatLng(29.985576,103.006022);
                    //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                    InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
                    String text = txtLatLng.getText().toString();
                    //显示InfoWindow
                    mBaiduMap.showInfoWindow(mInfoWindow);
                    speak(0,text);
                }
                return false;
            }
        });
    }
    private void addMarker(LatLng point){
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        mBaiduMap.addOverlay(option);
    }
    private void addMarker1(LatLng point){
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        mBaiduMap.addOverlay(option);
    }
    private void speak(int speaker,String text) {
        //若是每次都这样是不是会有内存问题呢？需要思考改进
        VoiceUtils utils=new VoiceUtils();
        utils.init(this,0);
        mSpeechSynthesizer=utils.getSyntheszer();

        this.mSpeechSynthesizer.speak(text);
    }
    private void init(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "联系我:798389920@qq.com", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
//侧边栏设置
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"这是配置", Toast.LENGTH_LONG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//侧边栏设置
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_information) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, NextActivity.class);
            MainActivity.this.startActivity(intent);
        } else if (id == R.id.nav_record) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, RanksActivity.class);
            MainActivity.this.startActivity(intent);

        } else if (id == R.id.nav_documents) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.login_register){
            //登录注册
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
