package com.code.tanwir.menu.activity.view_home;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import  com.code.tanwir.menu.R;

/**
 * Created by Tanwir on 06/02/2016.
 */
public class CustomList extends ArrayAdapter<String> {

    public String[] name;
    public String[] pb;
    private String[] urls;
    private Bitmap[] bitmaps;
    private Activity context;

    public CustomList(Activity context, Bitmap[] bitmaps,String[] name,String[] pb) {
        super(context, R.layout.list_item,name);
        this.context = context;
        this.bitmaps= bitmaps;
//        this.urls= urls;
        this.name=name;
        this.pb=pb;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        TextView textName= (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textPb= (TextView) listViewItem.findViewById(R.id.textViewPublisher);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageDownloaded);

        textName.setText(name[position]);
        textPb.setText(pb[position]);
        image.setImageBitmap(bitmaps[position]);
        return  listViewItem;
    }
}
