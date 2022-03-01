package com.example.quizapp;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static Upload fromString(String value){
        String[] uploadList = value.split("///");
        return new Upload(uploadList[0],uploadList[1]);
    }

    @TypeConverter
    public static String fromUpload(Upload upload){
        return upload.getName() + "///" + upload.getImageUrl();
    }
}
