package com.example.ydx.findding_application_test1;
//未用
/**
 * Created by ghjhh on 2018/2/20.
 */
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListName extends ListActivity {
     private String[] presidents={"北京","深圳","济南","广州","海南","香港","澳门"};

             @Override
     protected void onCreate(Bundle savedInstanceState) {
                 // TODO Auto-generated method stub
                 super.onCreate(savedInstanceState);
                 ListView listview=getListView();
                 //添加一个TextView作为表头
                TextView tvHeader=new TextView(ListName.this);
                 tvHeader.setText("队名");
                 listview.addHeaderView(tvHeader);
                 //添加一个TextView作为表尾
                 TextView tvFooter=new TextView(ListName.this);
                 tvFooter.setText("城市列表尾");
                 listview.addFooterView(tvFooter);
                listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,presidents));

            }
     @Override
     protected void onListItemClick(ListView parent, View view, int position, long id) {
                Toast.makeText(this, "You have selected "+presidents[position], Toast.LENGTH_SHORT).show();
            }
 }
