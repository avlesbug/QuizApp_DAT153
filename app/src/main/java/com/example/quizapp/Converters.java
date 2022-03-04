package com.example.quizapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class Converters {
    @TypeConverter
    public static byte[] fromBitmap(Bitmap value){
        /*
        if(value != null) {
            System.out.println("Converted image!!");
            System.out.println("Bitmap string: "+value.toString());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            value.compress(Bitmap.CompressFormat.JPEG, 80, stream);
            byte[] byteArray = stream.toByteArray();
            return byteArray;
        }

         */
        //ByteArrayOutputStream stream = new ByteArrayOutputStream();
        return "Image was null".getBytes();
    }

    @TypeConverter
    public static Bitmap fromByteArray(byte[] value){
        /*
        if(value != null) {
            Bitmap image = BitmapFactory.decodeByteArray(value, 0, value.length);
            return image;
        }

         */
        byte[] empty = "Image was null".getBytes();
        return BitmapFactory.decodeByteArray(empty,0,empty.length);
    }
}
