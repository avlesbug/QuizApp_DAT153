package com.example.quizapp;

import java.util.ArrayList;
import java.util.List;

public class ImageDB {
    public List<Upload> mUpl;

    public ImageDB(){
        mUpl = new ArrayList<>();
    }

    public void addImage(Upload image){
        mUpl.add(image);
    }

    public List<Upload> getUploads(){
        return mUpl;
    }

    public void setmUpl(List<Upload> mUpl) {
        this.mUpl = mUpl;
    }

    public List<String> getAllNames(){
        List<String> allImageNames = new ArrayList<>();
        for(Upload u : mUpl){
            allImageNames.add(u.getName());
        }
        return allImageNames;
    }

}
