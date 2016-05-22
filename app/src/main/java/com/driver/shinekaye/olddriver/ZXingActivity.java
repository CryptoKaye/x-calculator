package com.driver.shinekaye.olddriver;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by Shine Kaye on 2016/3/26.
 */

/**
 * Results of QRCode 二维码内容界面
 */
public class ZXingActivity extends AppCompatActivity {

    ImageView result;
    Bitmap resultBitmap;
    MakeQRCodeUtil mc = new MakeQRCodeUtil();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing_result);
        result = (ImageView) findViewById(R.id.result);
        Intent intent = getIntent();
        try {
            resultBitmap = mc.makeQRImage(intent.getStringExtra("data"), 1000, 1000);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        result.setImageBitmap(resultBitmap);
        mc.saveImageToGallery(this, resultBitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "一键分享");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                sendToFriends();
                break;
        }
        return true;
    }

    public class MakeQRCodeUtil {
        //根据指定内容生成自定义宽高的二维码图片
        public Bitmap makeQRImage(String content, int width, int height)
                throws WriterException {
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE
                    , width, height, hints);
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (bitMatrix.get(x, y)) {
                        if (x < width / 2 && y < height / 2) {
                            pixels[y * width + x] = 0xFF0094FF;// 蓝色
                            Integer.toHexString(new Random().nextInt());
                        } else if (x < width / 2 && y > height / 2) {
                            pixels[y * width + x] = 0xFFFED545;// 黄色
                        } else if (x > width / 2 && y > height / 2) {
                            pixels[y * width + x] = 0xFF5ACF00;// 绿色
                        } else {
                            pixels[y * width + x] = 0xFF000000;// 黑色
                        }
                    } else {
                        pixels[y * width + x] = 0xffffffff;// 白色
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        }


        //保存到相册
        public void saveImageToGallery(Context context, Bitmap bmp) {
            File appDir = new File(Environment.getExternalStorageDirectory(), "DriverCode");
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            String fileName = "QRcode.jpg";
            File file = new File(appDir, fileName);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(),
                        fileName, null);
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Toast.makeText(ZXingActivity.this, "图片已保存到:" + file, Toast.LENGTH_LONG).show();
        }
    }

    //分享到朋友圈
    private void sendToFriends() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        Uri imageUri = Uri.parse(Environment.getExternalStorageDirectory() + "/DriverCode/QRcode.jpg");
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "把二维码分享到..."));
    }
}

