package com.driver.shinekaye.olddriver;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shine Kaye on 2016/5/15.
 */
public class AddContent extends Activity implements View.OnClickListener {

    private String result;
    private Button saveBtn, deleteBtn;
    private ImageView imageView;
    private EditText editText;
    private VideoView videoView;
    private SecretAlbumDB secretAlbumDB;
    private SQLiteDatabase dbWriter;
    private File phoneFile, videoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontent);

        result = getIntent().getStringExtra("flag");
        saveBtn = (Button) findViewById(R.id.save);
        deleteBtn = (Button) findViewById(R.id.delete);
        editText = (EditText) findViewById(R.id.ettext);
        imageView = (ImageView) findViewById(R.id.c_img);
        videoView = (VideoView) findViewById(R.id.c_video);

        saveBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        secretAlbumDB = new SecretAlbumDB(this);
        dbWriter = secretAlbumDB.getWritableDatabase();

        init();

    }

    private void init() {

        if (result.equals("1")) {
            imageView.setVisibility(View.GONE);
            videoView.setVisibility(View.GONE);
        }
        if (result.equals("2")) {
            imageView.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.GONE);
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            phoneFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+getTime()+".jpg");
            camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(phoneFile));
            startActivityForResult(camera, 1);
        }
        if (result.equals("3")) {
            imageView.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
            Intent videoCamera = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            videoFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+getTime()+".mp4");
            videoCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));
            startActivityForResult(videoCamera, 2);
        }
    }


    private void addDB() {
        ContentValues cv = new ContentValues();
        cv.put(SecretAlbumDB.CONTENT, editText.getText().toString());
        cv.put(SecretAlbumDB.TIME, getTime());
        cv.put(SecretAlbumDB.PATH, phoneFile+"");
        cv.put(SecretAlbumDB.VIDEO, videoFile+"");
        dbWriter.insert(SecretAlbumDB.TABLE_NAME, null, cv);


    }

    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        String time = format.format(curDate);
        return time;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                addDB();
                finish();
                break;

            case R.id.delete:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Bitmap bitmap = BitmapFactory.decodeFile(phoneFile.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        }
        if (requestCode == 2) {
            videoView.setVideoURI(Uri.fromFile(videoFile));
            videoView.start();
        }
    }
}
