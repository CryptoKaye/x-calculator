package com.driver.shinekaye.olddriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Shine Kaye on 2016/3/26.
 */

/**
 * Content of the QRCode 二维码生成界面
 */
public class StartQRCodeActivity extends AppCompatActivity {

    EditText editText;
    Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_qrcode);
        editText = (EditText) findViewById(R.id.original);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editText.getText())) {
                    Intent intent = new Intent(StartQRCodeActivity.this, ZXingActivity.class);
                    intent.putExtra("data", editText.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(StartQRCodeActivity.this, "为什么要转换空白呢QAQ，请输入内容...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
