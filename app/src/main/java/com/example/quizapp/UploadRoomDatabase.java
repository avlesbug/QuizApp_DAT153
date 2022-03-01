package com.example.quizapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Upload.class}, version = 1)
public abstract class UploadRoomDatabase extends RoomDatabase {

    public abstract UploadDao UploadDao();

    private static UploadRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static UploadRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UploadRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UploadRoomDatabase.class, "upload_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more Uploads, just add them.
                UploadDao dao = INSTANCE.UploadDao();
                dao.deleteAll();

                Upload upload = new Upload("Person Personsen","imageUri");
                dao.insert(upload);
                upload = new Upload("Guy Guyson","imageUri");
                dao.insert(upload);
            });
        }
    };
}