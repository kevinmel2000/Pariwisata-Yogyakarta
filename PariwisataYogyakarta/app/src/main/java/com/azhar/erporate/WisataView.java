package com.azhar.erporate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.azhar.erporate.adapter.ImageLoader;

/**
 * Created by Azhar Rivaldi on 15/07/2019.
 */

public class WisataView extends AppCompatActivity {

    // Declare Variables
    String judul;
    String alamat;
    String detail;
    String gambar;
    ImageLoader imageLoader = new ImageLoader(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_wisata_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        judul = i.getStringExtra("judul");

        alamat = i.getStringExtra("alamat");

        detail = i.getStringExtra("detail");

        gambar = i.getStringExtra("gambar");

        TextView judulSi = (TextView) findViewById(R.id.judulSi);
        TextView alamatSI = (TextView) findViewById(R.id.alamatSi);
        TextView detailSi = (TextView) findViewById(R.id.detailSi);
        ImageView imgflag = (ImageView) findViewById(R.id.imgflag);

        judulSi.setText(judul);
        alamatSI.setText(alamat);
        detailSi.setText(detail);
        imageLoader.DisplayImage(gambar, imgflag);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}