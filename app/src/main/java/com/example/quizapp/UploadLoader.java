package com.example.quizapp;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UploadLoader {
    private DatabaseReference mDatabaseReferance;
    private PictureAdapter mPictureAdapter;
    private ImageDB imageDB;

    public UploadLoader(){
        imageDB = new ImageDB();
        //mUpl= new ArrayList<>();

        //path to databasefiles. Pictures are saved in "uploads" folder.
        mDatabaseReferance = FirebaseDatabase.getInstance().getReference("uploads");

        mDatabaseReferance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnap) {
                for (DataSnapshot postSnapshot : dataSnap.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    imageDB.addImage(upload);
                    //mUpl.add(upload);
                }
                //mPictureAdapter = new PictureAdapter(DatabaseActivity.this, mUpl);
            }
            // if error happens display a message
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public ImageDB getImageDB() {
        return imageDB;
    }
}
