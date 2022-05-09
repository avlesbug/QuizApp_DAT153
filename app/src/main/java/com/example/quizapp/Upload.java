package com.example.quizapp;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "upload_table")
public class Upload {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    private String mName;
    //private String mImageUrl;
    private String mImage;
    /*
    public Upload() {
//DO NOT DELETE THIS CONSTRUCTOR
    }

 */

    public Upload(String name, String mImage) {

        this.mName = name;
        this.mImage = mImage;

    }

    public String getName(){
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public int getUid() {
        return uid;
    }

    public String toString(){
        return mName + "///" + mImage.toString();
    }

}
