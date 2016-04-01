package com.driver.shinekaye.olddriver;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shine Kaye on 2016/3/27.
 */

/**
 * Morse Translation Activity 莫尔斯码转换界面
 */
public class MorseActivity extends AppCompatActivity {

    String result = "";
    EditText editText;
    Button btnTransfer, btnContransfer;
    String string;
    char item;
    String transferResult = "";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse);

        //初始化UI组件
        editText = (EditText) findViewById(R.id.strings);
        btnTransfer = (Button) findViewById(R.id.btnTransfer);

        //BUTTON监听器
        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = editText.getText().toString();
                Pattern p = Pattern.compile("[\\u4E00-\\u9FA5]");
                Matcher m = p.matcher(string);
                //判断用户输入是否为空
                if (!TextUtils.isEmpty(string)) {
                    //正则表达式判断是否输入了中文字符
                    if (!m.find()) {
                        for (int i = 0  ; i < string.length(); i++) {
                            item = string.charAt(i);
                            if (item < 48 || item >57) {
                                transferResult += morseCharTranslate(item) + "/";
                            } else {
                                transferResult += morseSignTransfer(item) + "/";
                            }
                        }
                        callDialog();
                    }else {
                        Toast.makeText(MorseActivity.this, "不能输入汉字QAQ，请输入字母或者数字...", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MorseActivity.this, "为什么要转换空白呢QAQ，请输入内容...", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //BUTTON监听器
        btnContransfer = (Button) findViewById(R.id.btnConTransfer);
        btnContransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = editText.getText().toString();
                Pattern p = Pattern.compile("[\\u4E00-\\u9FA5]");
                Matcher m = p.matcher(string);
                //判断用户输入是否为空
                if (!TextUtils.isEmpty(string)) {
                    //正则表达式判断是否输入了中文字符
                    if (!m.find()) {
                        String[] params = string.split("/");
                        for (String str : params) {
                            transferResult += charMorseTransfer(str);
                        }
                        callDialog();
                    } else {
                        Toast.makeText(MorseActivity.this, "不能输入汉字QAQ，请输入字母或者数字...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MorseActivity.this, "为什么要转换空白呢QAQ，请输入内容...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //对话框
    private void callDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MorseActivity.this)
                .setTitle("Result")
                .setIcon(R.drawable.ask)
                .setMessage(transferResult);
        setPositiveButton(builder)
                .create()
                .show();
    }
    private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder) {
        return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(transferResult);
                Toast.makeText(MorseActivity.this, "结果已复制到剪贴板", Toast.LENGTH_SHORT).show();
                transferResult = "";
                editText.setText("");
            }
        });
    }

    //英文字符以及常见符号转化为莫尔斯码
    private String morseCharTranslate (char origin) {
        char tansfer = Character.toLowerCase(origin);
        switch (tansfer)
        {
            case 'a':
                result =  "．━";
                break;
            case 'b':
                result =  "━ ．．．";
                break;
            case 'c':
                result =  "━ ．━ ．";
                break;
            case 'd':
                result =  "━ ．．";
                break;
            case 'e':
                result =  "．";
                break;
            case 'f':
                result =  "．．━ ．";
                break;
            case 'g':
                result =  "━━ ．";
                break;
            case 'h':
                result =  "．．．．";
                break;
            case 'i':
                result =  "．．";
                break;
            case 'j':
                result =  "．━━━";
                break;
            case 'k':
                result =  "━ ．━";
                break;
            case 'l':
                result =  "．━ ．．";
                break;
            case 'm':
                result =  "━━";
                break;
            case 'n':
                result =  "━ ．";
                break;
            case 'o':
                result =  "━━━";
                break;
            case 'p':
                result =  "．━━ ．";
                break;
            case 'q':
                result =  "━━ ．━";
                break;
            case 'r':
                result =  "．━ ．";
                break;
            case 's':
                result =  "．．．";
                break;
            case 't':
                result =  "━";
                break;
            case 'u':
                result =  "．．━";
                break;
            case 'v':
                result =  "．．．━";
                break;
            case 'w':
                result =  "．━━";
                break;
            case 'x':
                result =  "━ ．．━";
                break;
            case 'y':
                result =  "━ ．━━";
                break;
            case 'z':
                result =  "━━ ．．";
                break;
            case 32:
                result = " ";
                break;

            case '?':
                result = "．．━━ ．．";
                break;
            case '.':
                result = "．━ ．━ ．━";
                break;
            case ':':
                result = "━━━ ．．．";
                break;
            case ',':
                result = "━━ ．．━━";
                break;
            case ';':
                result = "━ ．━ ．━ ．";
                break;
            case '=':
                result = "━ ．．．━";
                break;
            case '\'':
                result = "．━━━━ ．";
                break;
            case '/':
                result = "━ ．．━ ．";
                break;
            case '_':
                result = "．．━━ ．━";
                break;
            case '!':
                result = "━ ．━ ．━━";
                break;
            case '"':
                result = "．━ ．．━ ．";
                break;
            case '(':
                result = "━ ．━━ ．";
                break;
            case ')':
                result = "━ ．━━ ．━";
                break;
            case '$':
                result = "．．．━ ．．━";
                break;
            case '&':
                result = "． ．．．";
                break;
            case '@':
                result = "．━━ ．━ ．";
                break;
            case '0':
                result = "━━━━━";
                break;
        }
        return result;
    }

    //数字转化为莫尔斯码
    private String morseSignTransfer (char origin) {
        switch (origin)
        {
            case '1':
                result = "．━━━━";
                break;
            case '2':
                result = "．．━━━";
                break;
            case '3':
                result = "．．．━━";
                break;
            case '4':
                result = "．．．．━";
                break;
            case '5':
                result = "．．．．．";
                break;
            case '6':
                result = "━ ．．．．";
                break;
            case '7':
                result = "━━ ．．．";
                break;
            case '8':
                result = "━━━ ．．";
                break;
            case '9':
                result = "━━━━ ．";
                break;
            case 32:
                result = " ";
                break;
        }
        return result;
    }

    //莫尔斯码翻译
    public String charMorseTransfer(String morse) {
        switch (morse)
        {
            case "．━":
                result = "a";
                break;
            case "━ ．．．":
                result = "b";
                break;
            case "━ ．━ ．":
                result = "c";
                break;
            case "━ ．．":
                result = "d";
                break;
            case "．":
                result = "e";
                break;
            case "．．━ ．":
                result = "f";
                break;
            case "━━ ．":
                result = "g";
                break;
            case "．．．．":
                result = "h";
                break;
            case "．．":
                result = "i";
                break;
            case "．━━━":
                result = "j";
                break;
            case "━ ．━":
                result = "k";
                break;
            case "．━ ．．":
                result = "l";
                break;
            case "━━":
                result = "m";
                break;
            case "━ ．":
                result = "n";
                break;
            case "━━━":
                result = "o";
                break;
            case "．━━ ．":
                result = "p";
                break;
            case "━━ ．━":
                result = "q";
                break;
            case "．━ ．":
                result = "r";
                break;
            case "．．．":
                result = "s";
                break;
            case "━":
                result = "t";
                break;
            case "．．━":
                result = "u";
                break;
            case "．．．━":
                result = "v";
                break;
            case "．━━":
                result = "w";
                break;
            case "━ ．．━":
                result = "x";
                break;
            case "━ ．━━":
                result = "y";
                break;
            case "━━ ．．":
                result = "z";
                break;
            case "━━━━━":
                result = "0";
                break;
            case "．━━━━":
                result = "1";
                break;
            case "．．━━━":
                result = "2";
                break;
            case "．．．━━":
                result = "3";
                break;
            case "．．．．━":
                result = "4";
                break;
            case "．．．．．":
                result = "5";
                break;
            case "━ ．．．．":
                result = "6";
                break;
            case "━━ ．．．":
                result = "7";
                break;
            case "━━━ ．．":
                result = "8";
                break;
            case "━━━━ ．":
                result = "9";
                break;
            case "．━ ．━ ．━":
                result = ".";
                break;
            case "━━━ ．．．":
                result = ":";
                break;
            case "━━ ．．━━":
                result = ",";
                break;
            case "━ ．━ ．━ ．":
                result = ";";
                break;
            case "．．━━ ．．":
                result = "?";
                break;
            case "━ ．．．━":
                result = "=";
                break;
            case "．━━━━ ．":
                result = "\'";
                break;
            case "━ ．．━ ．":
                result = "/";
                break;
            case "━ ．━ ．━━":
                result = "!";
                break;
            case "．．━━ ．━":
                result = "_";
                break;
            case "．━ ．．━ ．":
                result = "\"";
                break;
            case "━ ．━━ ．":
                result = "(";
                break;
            case "━ ．━━ ．━":
                result = ")";
                break;
            case "．．．━ ．．━":
                result = "$";
                break;
            case "． ．．．":
                result = "&";
                break;
            case "．━━ ．━ ．":
                result = "@";
                break;
            case " ":
                result = " ";
                break;
        }
        return result;
    }
}
