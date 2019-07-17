package com.azhar.erporate;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.azhar.erporate.adapter.JSONfunctions;
import com.azhar.erporate.adapter.ListViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Azhar Rivaldi on 15/07/2019.
 */

public class ListWisataActivity extends AppCompatActivity{

    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    public static String JUDUL = "nama_pariwisata";
    public static String ALMAT = "alamat_pariwisata";
    public static String DETAIL = "detail_pariwisata";
    public static String GAMBAR = "gambar_pariwisata";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wisata);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new DownloadJSON().execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class DownloadJSON extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog = new ProgressDialog(ListWisataActivity.this);
            mProgressDialog.setTitle("Sedang Mengambil Data Server");
            mProgressDialog.setMessage("Tunggu sebentar...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            arraylist = new ArrayList<HashMap<String, String>>();
            jsonobject = JSONfunctions.getJSONfromURL("http://erporate.com/bootcamp/jsonBootcamp.php");

            try {

                jsonarray = jsonobject.getJSONArray("data");

                for (int i = 0; i < jsonarray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);
                    // Retrive JSON Objects
                    map.put("nama_pariwisata", jsonobject.getString("nama_pariwisata"));
                    map.put("alamat_pariwisata", jsonobject.getString("alamat_pariwisata"));
                    map.put("detail_pariwisata", jsonobject.getString("detail_pariwisata"));
                    map.put("gambar_pariwisata", jsonobject.getString("gambar_pariwisata"));
                    // Set the JSON Objects into the array
                    arraylist.add(map);
                }

            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

            listview = (ListView) findViewById(R.id.listview);
            adapter = new ListViewAdapter(ListWisataActivity.this, arraylist);
            listview.setAdapter(adapter);
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ListWisataActivity.this);
        builder.setMessage("Ingin keluar dari Aplikasi?");
        builder.setCancelable(true);
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}