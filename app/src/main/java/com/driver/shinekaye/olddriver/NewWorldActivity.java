package com.driver.shinekaye.olddriver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shine Kaye on 2016/3/26.
 */

/**
 * New World 真实密码输入后的界面
 */
public class NewWorldActivity extends AppCompatActivity {

    //初始化列表项
    private String[] titles = new String[] {
            "自定义二维码","摩斯电码翻译器","设置","创建隐藏项目"
    };
    private String[] contents = new String[] {
            "导出你自己的二维码","通过莫尔斯码密匙交流","密码更改与帮助","隐藏图片、隐藏视频、隐藏笔记"
    };
    private int[] imageIds = new int[] {
            R.drawable.qrcode,R.drawable.morsecode,R.drawable.settings,R.drawable.pic
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newworld);
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("header", imageIds[i]);
            listItem.put("title", titles[i]);
            listItem.put("content", contents[i]);
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.activity_newworld_item,
                new  String[] { "title","header", "content"}, new int[]{R.id.title,R.id.header,R.id.content});
        ListView list = (ListView) findViewById(R.id.mylist);
        list.setAdapter(simpleAdapter);
        //列表项点击监听
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(NewWorldActivity.this, StartQRCodeActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(NewWorldActivity.this, MorseActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(NewWorldActivity.this, SettingsActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(NewWorldActivity.this, SecretAlbumActivity.class));
                        break;
                }

            }
        });





    }



}
