package com.driver.shinekaye.olddriver;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import static android.view.View.OnClickListener;

/**
 * Calculator Main Activity 计算器初始界面
 */

public class CalculatorActivity extends AppCompatActivity {

    //数值数组
    private Button[] number = new Button[11];
    //计算符数组
    private Button[] count = new Button[5];
    //显示框
    private TextView display;
    //清除键
    private Button btnClear;
    //保存运算符
    private String lastCount;
    //是否需要清空显示框中的值
    private Boolean clearFlag;
    //是否是首次输入
    private Boolean firstFlag;
    //运算结果
    private double result;

    //默认真实密码和伪密码
    public static final String PASSWORD = "94694694666";
    public static final String FAKE_PASSWORD = "44944";

    //初始化
    public CalculatorActivity() {
        result = 0.0;
        firstFlag = true;
        clearFlag = false;
        lastCount = "=";
    }

    SharedPreUtil sharedPreUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //实例化sharedPreUtil对象
        sharedPreUtil = new SharedPreUtil(this);

        //获取运算符加减乘除等
        count[0] = (Button) findViewById(R.id.plus);
        count[1] = (Button) findViewById(R.id.subtract);
        count[3] = (Button) findViewById(R.id.multiply);
        count[2] = (Button) findViewById(R.id.divide);
        count[4] = (Button) findViewById(R.id.equal);
        //获取数字
        number[0] = (Button) findViewById(R.id.num0);
        number[1] = (Button) findViewById(R.id.num1);
        number[2] = (Button) findViewById(R.id.num2);
        number[3] = (Button) findViewById(R.id.num3);
        number[4] = (Button) findViewById(R.id.num4);
        number[5] = (Button) findViewById(R.id.num5);
        number[6] = (Button) findViewById(R.id.num6);
        number[7] = (Button) findViewById(R.id.num7);
        number[8] = (Button) findViewById(R.id.num8);
        number[9] = (Button) findViewById(R.id.num9);
        number[10] = (Button) findViewById(R.id.dot);
        //初始化显示框
        display = (TextView) findViewById(R.id.display);
        display.setText("0.0");
        //实例化监听器对象
        NumberAction na = new NumberAction();
        CountAction ca = new CountAction();
        for (Button btn : count) {
            btn.setOnClickListener(ca);
        }
        for (Button btn : number) {
            btn.setOnClickListener(na);
        }
        //btnClear清除键监听器
        btnClear = (Button) findViewById(R.id.clear);
        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("0.0");
                result = 0;
                firstFlag = true;
                clearFlag = false;
                lastCount = "=";
            }
        });
    }

    //数字按键监听类
    private class NumberAction implements OnClickListener {
        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            String input = btn.getText().toString();
            if (firstFlag) {
                if (input.equals(".")) {
                    return;
                }
                if (display.getText().toString().equals("0.0")) {
                    display.setText("");
                }
                firstFlag = false;
        }else {
                String displayStr = display.getText().toString();

                if (displayStr.indexOf(".") != -1 && input.equals(".")) {
                    return;
                }
                if (displayStr.equals("-") && input.equals(".")) {
                    return;
                }
                if (displayStr.equals("0") && !input.equals(".")) {
                    return;
                }
            }
            if (clearFlag) {
                display.setText("");
                clearFlag = false;
            }
            display.setText(display.getText().toString() + input);
    }
    }

    //运算符监听类
    private class CountAction implements OnClickListener {
        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            String inputCount = (String) btn.getText();
            if (firstFlag) {
                if (inputCount.equals("-")) {
                    display.setText("-");
                    firstFlag = false;
                }
            } else {
                if (!clearFlag) {
                    calculate(Double.parseDouble(display.getText().toString()));
                }
                lastCount = inputCount;
                clearFlag = true;
            }
        }

        //计算函数
        private void calculate(double x) {
            if (lastCount.equals("+")) {
                result += x;
            } else if (lastCount.equals("-")) {
                result -= x;
            } else if (lastCount.equals("×")) {
                result *= x;
            } else if (lastCount.equals("÷")) {
                result /= x;
            } else if (lastCount.equals("=")) {
                result = x;
                //密码触发判断
                if (sharedPreUtil.getChanged("PASSWORD")) {
                    if (sharedPreUtil.getChanged("FAKE_PASSWORD")) {
                        if (display.getText().toString().equals(sharedPreUtil.getPassword("PASSWORD"))) {
                            callDialog();
                        } else if (display.getText().toString().equals(sharedPreUtil.getPassword("FAKE_PASSWORD"))) {
                            startActivity(new Intent(CalculatorActivity.this, FakeActivity.class));
                        }
                    } else {
                        if (display.getText().toString().equals(sharedPreUtil.getPassword("PASSWORD"))) {
                            callDialog();
                        } else if (display.getText().toString().equals(FAKE_PASSWORD)) {
                            startActivity(new Intent(CalculatorActivity.this, FakeActivity.class));
                        }
                    }
                } else {
                    if (sharedPreUtil.getChanged("FAKE_PASSWORD")){
                        if (display.getText().toString().equals(PASSWORD)) {
                            callDialog();
                        } else if (display.getText().toString().equals(sharedPreUtil.getPassword("FAKE_PASSWORD"))){
                            startActivity(new Intent(CalculatorActivity.this, FakeActivity.class));
                        }
                    } else {
                        if (display.getText().toString().equals(PASSWORD)) {
                            callDialog();
                        } else if (display.getText().toString().equals(FAKE_PASSWORD)){
                            startActivity(new Intent(CalculatorActivity.this, FakeActivity.class));
                        }
                    }
                }
            }
            display.setText("" + result);

        }
        //对话框
        private void callDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(CalculatorActivity.this)
                    .setTitle("New World")
                    .setIcon(R.drawable.ask)
                    .setMessage("你打开了新世界的大门");
                    setPositiveButton(builder)
                    .create()
                    .show();
        }
        private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder) {
            return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(CalculatorActivity.this, NewWorldActivity.class));
                }
            });
        }
    }
}

