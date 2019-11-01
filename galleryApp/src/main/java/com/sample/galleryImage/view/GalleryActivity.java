package com.sample.galleryImage.view;


import android.app.Application;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.sample.galleryImage.R;
import com.sample.galleryImage.model.GalleryModel;
import com.sample.galleryImage.utilis.Constants;
import com.sample.galleryImage.utilis.permissionhelper.ActivityManagePermission;
import com.sample.galleryImage.utilis.permissionhelper.PermissionResult;
import com.sample.galleryImage.view.adapter.PhotoAdapter;

import java.util.ArrayList;


public class GalleryActivity extends ActivityManagePermission implements AdapterView.OnItemClickListener {


    boolean isFolder;
    GridView gridView_GalleryImage;
    GalleryActivity getActivityContext = null;

    public  ArrayList<GalleryModel> all_gallery = new ArrayList<>();


    PhotoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityContext = GalleryActivity.this;
        initView();
        permissionCheck();

    }


    private void permissionCheck() {


        if (getActivityContext != null) {
            askCompactPermissions(Constants.PERMISSION_LIST, new PermissionResult() {
                @Override
                public void permissionGranted() {
                    readingGalleryImages();

                }

                @Override
                public void permissionDenied() {
                    Toast.makeText(getActivityContext, "The app was not allowed to read or write to your storage. Hence,Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }

                @Override
                public void permissionForeverDenied() {
                    Toast.makeText(getActivityContext, "The app was not allowed to read or write to your storage. Hence,Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void initView() {
        gridView_GalleryImage = (GridView) findViewById(R.id.gridView_gallery);
        gridView_GalleryImage.setOnItemClickListener(getActivityContext);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        switch (v.getId()) {
            case R.id.gridView_gallery:
                break;
            default:
                break;
        }
    }

    public ArrayList<GalleryModel> readingGalleryImages() {

        all_gallery.clear();

        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            Log.e("Column", absolutePathOfImage);
            Log.e("Folder", cursor.getString(column_index_folder_name));

            for (int i = 0; i < all_gallery.size(); i++) {
                if (all_gallery.get(i).getFoldername().equals(cursor.getString(column_index_folder_name))) {
                    isFolder = true;
                    int_position = i;
                    break;
                } else {
                    isFolder = false;
                }
            }


            if (isFolder) {

                ArrayList<String> al_path = new ArrayList<>();
                al_path.addAll(all_gallery.get(int_position).getmImagePath());
                al_path.add(absolutePathOfImage);
                all_gallery.get(int_position).setmImagePath(al_path);

            } else {
                ArrayList<String> al_path = new ArrayList<>();
                al_path.add(absolutePathOfImage);
                GalleryModel obj_model = new GalleryModel();
                obj_model.setFoldername(cursor.getString(column_index_folder_name));
                obj_model.setmImagePath(al_path);

                all_gallery.add(obj_model);


            }


        }
        for (int i = 0; i < all_gallery.size(); i++) {
            Log.e("FOLDER", all_gallery.get(i).getFoldername());
            for (int j = 0; j < all_gallery.get(i).getmImagePath().size(); j++) {
                Log.e("FILE", all_gallery.get(i).getmImagePath().get(j));
            }
        }
        adapter = new PhotoAdapter(getApplicationContext(), all_gallery);
        gridView_GalleryImage.setAdapter(adapter);
        return all_gallery;
    }
}
