package com.example.quizapp;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
