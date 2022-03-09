package com.example.quizapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private UploadDao dao;
    private UploadRoomDatabase db;
    private Context context;

    @Before
    public void setupDb(){
        context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, UploadRoomDatabase.class).build();
        dao = db.UploadDao();
    }

    @After
    public void closeDb(){
        db.close();
    }

    @Test
    public void entryCountTest(){
        // Asserts that the db is empty
        assertThat(dao.getAll().size(), equalTo(0));

        // Adds a person to db
        //context.getContentResolver();
        //Resources res = context.getResources();
        //Bitmap b =  BitmapFactory.decodeResource(res, R.drawable.jon);
        //Bitmap bitmap;
        //bitmap = Bitmap.createScaledBitmap(b, 10, 10, false);
        Upload p1 = new Upload("Test", "test");
        dao.insert(p1);

        // Asserts that the db now has one person
        assertThat(dao.getAll().size(), equalTo(1));
    }
}