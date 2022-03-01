package com.example.quizapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.database.annotations.NotNull;

@Entity(tableName = "upload_table")
public class Upload {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    private String mName;
    private String mImageUrl;
/*
    public Upload() {
//DO NOT DELETE THIS CONSTRUCTOR
    }

 */

    public Upload(@NotNull String name, String imageUrl) {

        mName = name;
        mImageUrl = imageUrl;

    }

    public String getName(){
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public int getUid() {
        return uid;
    }

}
