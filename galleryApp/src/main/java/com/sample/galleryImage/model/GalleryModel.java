package com.sample.galleryImage.model;

import java.util.ArrayList;

/*
 * Project Name : GalleryApp
 * Created by : SATHISH KUMAR R
 * Created on :31-10-2019 20:35
 * Updated on :
 * File Name : null.java
 * ClassName : GalleryModel
 * Module Name : app
 * Desc :
 */
public class GalleryModel {


    String foldername;
    ArrayList<String> mImagePath;

    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    public ArrayList<String> getmImagePath() {
        return mImagePath;
    }

    public void setmImagePath(ArrayList<String> mImagePath) {
        this.mImagePath = mImagePath;
    }



}
