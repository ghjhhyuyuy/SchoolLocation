package com.example.ydx.findding_application_test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by ghjhh on 2018/2/20.
 */
//未用
public class RanksActivity extends Activity {
    private ListView listview;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> data;
    private TextView txtView;
    private int recLen = 0;
    private int mecLen = 0;
    private int hecLen = 0;
    private Button bt;
    boolean flag = true;
    private static Bundle outState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranks_panel);
        txtView = (TextView) findViewById(R.id.txtTime);
//        if(outState!=null){
//            flag= outState.getBoolean("flag");
//        }
        if(flag){
            new Thread(new MyThread()).start();
        }
        bt=(Button)findViewById(R.id.return1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(RanksActivity.this, MainActivity.class);
                RanksActivity.this.startActivity(intent);
            }
        });
        listview = (ListView) findViewById(R.id.lvArray);
//        TextView tvHeader=new TextView(RanksActivity.this);
//        tvHeader.setText("名称                    状态");
//        listview.addHeaderView(tvHeader);
        //填充数据
        putData();
        //这里使用当前的布局资源作为ListView的模板。
        //使用这种方式，SimpleAdapter会忽略ListView控件，仅以ListView之外的控件作为模板。
        simpleAdapter = new SimpleAdapter(RanksActivity.this, data,
                R.layout.example, new String[]{
                "name", "ss"}, new int[]{R.id.tvName,
                R.id.tvSS});
        listview.setAdapter(simpleAdapter);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (outState != null) {
            outState.clear();
            outState = null;
        }
        outState = new Bundle();
        outState.putBoolean("flag",false);
    }
//    @Override
//    protected void onSaveInstanceState(Bundle savedInstanceState) {
//        savedInstanceState.putBoolean("flag", false);
//        super.onSaveInstanceState(savedInstanceState);
//        // etc.
//
//    }
    private void putData() {
        data = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "简爱");
        map1.put("ss", "游戏中");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", "陌陌");
        map2.put("ss", "死亡");
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("name", "汐颜");
        map3.put("ss", "游戏中");
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("name", "花仙子");
        map4.put("ss", " 游戏中");
        data.add(map1);
        data.add(map2);
        data.add(map3);
        data.add(map4);
    }

    final Handler handler = new Handler() {          // handle
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    recLen++;
                    if(recLen==60){
                        recLen=0;
                        mecLen++;
                        if(mecLen==60){
                            hecLen++;
                            mecLen=0;
                        }
                    }
                    txtView.setText("" + hecLen+":"+mecLen+":"+recLen);
            }
            super.handleMessage(msg);
        }
    };

    public class MyThread implements Runnable {      // thread
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);     // sleep 1000ms
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (Exception e) {
                }
            }
        }
    }
}
