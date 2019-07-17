package com.azhar.erporate.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhar.erporate.ListWisataActivity;
import com.azhar.erporate.R;
import com.azhar.erporate.WisataView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Azhar Rivaldi on 15/07/2019.
 */

public class ListViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapter(Context context, ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        TextView judul;
        TextView alamat;
        TextView detail;
        ImageView gambar;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        @SuppressLint("ViewHolder") View itemView = inflater.inflate(R.layout.listview_item, parent, false);
        resultp = data.get(position);

        judul = (TextView) itemView.findViewById(R.id.judul);
        alamat = (TextView) itemView.findViewById(R.id.alamat);
        detail = (TextView) itemView.findViewById(R.id.detail);
        gambar = (ImageView) itemView.findViewById(R.id.gambar);

        judul.setText(resultp.get(ListWisataActivity.JUDUL));
        alamat.setText(resultp.get(ListWisataActivity.ALMAT));
        detail.setText(resultp.get(ListWisataActivity.DETAIL));
        imageLoader.DisplayImage(resultp.get(ListWisataActivity.GAMBAR), gambar);

        itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, WisataView.class);
                // Pass all data rank
                intent.putExtra("judul", resultp.get(ListWisataActivity.JUDUL));
                // Pass all data country
                intent.putExtra("alamat", resultp.get(ListWisataActivity.ALMAT));
                // Pass all data population
                intent.putExtra("detail", resultp.get(ListWisataActivity.DETAIL));
                // Pass all data flag
                intent.putExtra("gambar", resultp.get(ListWisataActivity.GAMBAR));
                // Start SingleItemView Class
                context.startActivity(intent);

            }
        });
        return itemView;
    }
}
