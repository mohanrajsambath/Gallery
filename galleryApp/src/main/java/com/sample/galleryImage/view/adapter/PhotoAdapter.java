package com.sample.galleryImage.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.sample.galleryImage.R;
import com.sample.galleryImage.model.GalleryModel;

import java.util.ArrayList;

/*
 * Project Name : AndelaChallengeProject
 * Created by : SATHISH KUMAR R
 * Created on :31-10-2019 23:30
 * Updated on :
 * File Name : null.java
 * ClassName : PhotoAdapter
 * Module Name : app
 * Desc :
 */
public class PhotoAdapter extends ArrayAdapter<GalleryModel> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<GalleryModel> mArrayList_galleryList = new ArrayList<>();

    public PhotoAdapter(@NonNull Context context, ArrayList<GalleryModel> al_galleryItem) {
        super(context, R.layout.item_photogallery);
        this.context = context;
        this.mArrayList_galleryList = al_galleryItem;
    }


    @Override
    public int getCount() {

        Log.e("ADAPTER LIST SIZE", mArrayList_galleryList.size() + "");
        return mArrayList_galleryList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (mArrayList_galleryList.size() > 0) {
            return mArrayList_galleryList.size();
        } else {
            return 1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photogallery, parent, false);
            viewHolder.textView_Foldername = (TextView) convertView.findViewById(R.id.textView_Foldername);
            viewHolder.textView_count = (TextView) convertView.findViewById(R.id.textView_count);
            viewHolder.imageView_photo = (ImageView) convertView.findViewById(R.id.imageView_photo);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView_Foldername.setText(mArrayList_galleryList.get(position).getFoldername());
        viewHolder.textView_count.setText(mArrayList_galleryList.get(position).getmImagePath().size()+"");



        Glide.with(context).load("file://" + mArrayList_galleryList.get(position).getmImagePath().get(0))
                               .into(viewHolder.imageView_photo);


        return  convertView;
    }


    private static class ViewHolder {
        TextView textView_count, textView_Foldername;
        ImageView imageView_photo;


    }
}
