package com.example.wagoner;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ImageAdapter extends BaseAdapter {


    private int[] images = {R.drawable.notes_math, R.drawable.notes_math2, R.drawable.example_input};
    private String[] text = {"Calculus 2", "Division HW", "Algebra"};

    Context ctx;

    ImageAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView = convertView;

        if(gridView == null) {
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.custom_image_layout, null);
        }

        ImageView i1 = (ImageView)gridView.findViewById(R.id.testImage);
        TextView tl = (TextView)gridView.findViewById(R.id.wheelID);
        i1.setImageResource(images[position]); ///WARNING CHANGE
        tl.setText(text[position]);

        return gridView;
    }



}
