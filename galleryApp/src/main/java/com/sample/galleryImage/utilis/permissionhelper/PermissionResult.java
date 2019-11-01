package com.sample.galleryImage.utilis.permissionhelper;

/*
 * Project Name : AndelaChallengeProject
 * Created by : SATHISH KUMAR R
 * Created on :01-11-2019 13:17
 * Updated on :
 * File Name : PermissionResult.java
 * ClassName :
 * Module Name : app
 * Desc :
 */

public interface PermissionResult {

    void permissionGranted();

    void permissionDenied();

    void permissionForeverDenied();

}
