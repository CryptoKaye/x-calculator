package com.driver.shinekaye.olddriver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by Shine Kaye on 2016/3/31.
 */

/**
 * About Author 关于作者界面
 */
public class AboutActivity extends AppCompatActivity {

    Button btnme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings_about);
        btnme = (Button) findViewById(R.id.btnme);
        btnme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
