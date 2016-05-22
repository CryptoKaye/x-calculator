package com.driver.shinekaye.olddriver;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;



/**
 * Created by Shine Kaye on 2016/5/11.
 */
public class SecretAlbumActivity extends AppCompatActivity implements View.OnClickListener {

    private Button textBtn, imgBtn, videoBtn;
    private ListView list;

    private Intent i;
    private SecretAlbumDB secretAlbum;
    private SQLiteDatabase dbReader;
    private MyAdapter adapter;
    private Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secretalbum);
        initView();
    }



    public void initView() {
        list = (ListView) findViewById(R.id.list);
        textBtn = (Button) findViewById(R.id.text);
        imgBtn = (Button) findViewById(R.id.img);
        videoBtn = (Button) findViewById(R.id.video);

        textBtn.setOnClickListener(this);
        imgBtn.setOnClickListener(this);
        videoBtn.setOnClickListener(this);

        secretAlbum = new SecretAlbumDB(this);
        dbReader = secretAlbum.getReadableDatabase();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                Intent i = new Intent(SecretAlbumActivity.this, SelectActivity.class);
                i.putExtra(SecretAlbumDB.ID, cursor.getInt(cursor.getColumnIndex(SecretAlbumDB.ID)));
                i.putExtra(SecretAlbumDB.CONTENT, cursor.getString(cursor.getColumnIndex(SecretAlbumDB.CONTENT)));
                i.putExtra(SecretAlbumDB.TIME, cursor.getString(cursor.getColumnIndex(SecretAlbumDB.TIME)));
                i.putExtra(SecretAlbumDB.PATH, cursor.getString(cursor.getColumnIndex(SecretAlbumDB.PATH)));
                i.putExtra(SecretAlbumDB.VIDEO, cursor.getString(cursor.getColumnIndex(SecretAlbumDB.VIDEO)));
                startActivity(i);

            }
        });


    }

   @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }

    @Override
    public void onClick(View v) {
        i = new Intent(this, AddContent.class);

        switch (v.getId()) {
            case R.id.text:
                i.putExtra("flag", "1");
                startActivity(i);
                break;

            case R.id.img:
                i.putExtra("flag", "2");
                startActivity(i);
                break;

            case R.id.video:
                i.putExtra("flag", "3");
                startActivity(i);
                break;
        }

    }

    public void selectDB() {
        cursor = dbReader.query(SecretAlbumDB.TABLE_NAME, null, null, null, null, null, null);
        adapter = new MyAdapter(this, cursor);
        list.setAdapter(adapter);


    }
}
