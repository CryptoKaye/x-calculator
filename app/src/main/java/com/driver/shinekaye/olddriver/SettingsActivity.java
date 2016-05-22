package com.driver.shinekaye.olddriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shine Kaye on 2016/3/27.
 */

/**
 * Settings Activity 设置界面
 */
public class SettingsActivity extends AppCompatActivity {

    //列表项初始化
    private String[] titles = new String[]{
            "真密码设置", "伪密码设置","帮助","关于"
    };
    private String[] contents = new String[]{
            "设置进入新世界的真实密码", "设置进入新世界的伪密码","你还需要学习","火前留名"
    };
    private int[] imageIds = new int[]{
            R.drawable.truesecret, R.drawable.fakesecret,R.drawable.help,R.drawable.aboutme
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
                new String[]{"title", "header", "content"}, new int[]{R.id.title, R.id.header, R.id.content});
        ListView list = (ListView) findViewById(R.id.mylist);
        list.setAdapter(simpleAdapter);
        //列表项点击监听器
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(SettingsActivity.this, PasswordActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(SettingsActivity.this, FakePasswordActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(SettingsActivity.this, HelpActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
                        break;
                }

            }
        });
    }




}

