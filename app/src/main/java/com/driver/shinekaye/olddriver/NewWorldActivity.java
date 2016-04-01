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
 * Created by Shine Kaye on 2016/3/26.
 */

/**
 * New World 真实密码输入后的界面
 */
public class NewWorldActivity extends AppCompatActivity {

    //初始化列表项
    private String[] titles = new String[] {
            "自定义二维码","摩斯电码翻译器","设置"
    };
    private String[] contents = new String[] {
            "无形飙车，最为致命","拒绝吃枣，拒绝药丸","开车的正确方式"
    };
    private int[] imageIds = new int[] {
            R.drawable.qrcode,R.drawable.morsecode,R.drawable.settings
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
                }

            }
        });
    }


}
