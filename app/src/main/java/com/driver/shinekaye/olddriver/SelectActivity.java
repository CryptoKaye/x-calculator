package com.driver.shinekaye.olddriver;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by Shine Kaye on 2016/5/15.
 */
public class SelectActivity extends Activity implements View.OnClickListener {

    private Button deleteBtn, backBtn;
    private ImageView imageView;
    private VideoView videoView;
    private TextView textView;
    private SecretAlbumDB secretAlbumDB;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seclect);

        deleteBtn = (Button) findViewById(R.id.s_delete);
        backBtn = (Button) findViewById(R.id.s_back);
        imageView = (ImageView) findViewById(R.id.s_img);
        videoView = (VideoView) findViewById(R.id.s_video);
        textView = (TextView) findViewById(R.id.s_text);

        deleteBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);


        secretAlbumDB = new SecretAlbumDB(this);
        dbWriter = secretAlbumDB.getWritableDatabase();

        if (getIntent().getStringExtra(SecretAlbumDB.PATH).equals("null")) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
        }

        if (getIntent().getStringExtra(SecretAlbumDB.VIDEO).equals("null")) {
            videoView.setVisibility(View.GONE);
        } else {
            videoView.setVisibility(View.VISIBLE);
        }

        textView.setText(getIntent().getStringExtra(SecretAlbumDB.CONTENT));
        Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra(SecretAlbumDB.PATH));
        imageView.setImageBitmap(bitmap);
        videoView.setVideoURI(Uri.parse(getIntent().getStringExtra(SecretAlbumDB.VIDEO)));
        videoView.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.s_delete:
                deleteData();
                finish();
                break;

            case R.id.s_back:
                finish();
                break;
        }
    }

    public void deleteData() {
        dbWriter.delete(SecretAlbumDB.TABLE_NAME, "_id="+getIntent().getIntExtra(SecretAlbumDB.ID, 0), null);
    }
}
