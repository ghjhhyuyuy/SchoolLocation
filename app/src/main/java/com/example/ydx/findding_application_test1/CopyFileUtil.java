package com.example.ydx.findding_application_test1;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Created by ghjhh on 2018/2/25.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
//未用
public class CopyFileUtil extends Activity
{
    private Button m_btn = null;
    private final static String FROMPATH = "../../../../DB/location.db/";
    private final static String TOPATH = "/mnt/sdcard/B/";

    /** Called when the activity is first created. */
   // @Override
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ranks_panel);
//        m_btn = (Button)findViewById(R.id.button1);
//        m_btn.setOnClickListener(new OnClickListener()
//        {
//
//            @Override
//            public void onClick(View v)
//            {
//// TODO Auto-generated method stub
//                if(copy(FROMPATH, TOPATH)==0)
//                {
//                    Toast.makeText(Copy_File.this,"文件拷贝成功！！！",20000).show();
//                }else
//                {
//                    Toast.makeText(Copy_File.this,"文件拷贝失败！！！",20000).show();
//                }
//            }
//
//        });
//    }

    public int copy(String fromFile, String toFile)
    {
//要复制的文件目录
        File[] currentFiles;
        File root = new File(fromFile);
//如同判断SD卡是否存在或者文件是否存在
//如果不存在则 return出去
        if(!root.exists())
        {
            return -1;
        }
//如果存在则获取当前目录下的全部文件 填充数组
        currentFiles = root.listFiles();

//目标目录
        File targetDir = new File(toFile);
//创建目录
        if(!targetDir.exists())
        {
            targetDir.mkdirs();
        }
//遍历要复制该目录下的全部文件
        for(int i= 0;i<currentFiles.length;i++)
        {
            if(currentFiles[i].isDirectory())//如果当前项为子目录 进行递归
            {
                copy(currentFiles[i].getPath() + "/", toFile + currentFiles[i].getName() + "/");

            }else//如果当前项为文件则进行文件拷贝
            {
                CopySdcardFile(currentFiles[i].getPath(), toFile + currentFiles[i].getName());
            }
        }
        return 0;
    }


    //文件拷贝
//要复制的目录下的所有非子目录(文件夹)文件拷贝
    public int CopySdcardFile(String fromFile, String toFile)
    {

        try
        {
            InputStream fosfrom = new FileInputStream(fromFile);
            OutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0)
            {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();
            return 0;

        } catch (Exception ex)
        {
            return -1;
        }
    }


}
