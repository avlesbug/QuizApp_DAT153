package com.example.quizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NewAddActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.questionlistsql.REPLY";
    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText mEditUploadView;
    private String mImageUri;
    private Button mButtonChoosePicture;
    private Button mButtonSaveEntry;
    private EditText mEditTextPictureName;
    private ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add);
        mEditUploadView = findViewById(R.id.edit_text_picture_name);
        mButtonChoosePicture = findViewById(R.id.select_image);
        mButtonSaveEntry = findViewById(R.id.button_uploader);
        mEditTextPictureName = findViewById(R.id.edit_text_picture_name);
        mImageView = findViewById(R.id.image_view);

        mButtonSaveEntry.setOnClickListener(view -> {
            saveEntry();
        });


        mButtonChoosePicture.setOnClickListener(view -> {
            openFile();
        });
    }

    private void openFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //retrieving picture, if pick image request is true/1, and result is a picture, and data is not null, then you """download""" the picture
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData().toString();
            System.out.println("Image path: "+data.getData().toString());
            mImageView.setImageURI(data.getData());

        }
    }

    private void saveEntry() {
        UploadRepository repo = new UploadRepository(this.getApplication());
        if (mImageUri != null) {
            if (TextUtils.isEmpty(mEditUploadView.getText())) {
                Toast.makeText(this, "You need to type a name!", Toast.LENGTH_SHORT).show();
            } else {
                String name = mEditUploadView.getText().toString();
                getContentResolver().takePersistableUriPermission(Uri.parse(mImageUri), Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Upload upload = new Upload(name,mImageUri);
                repo.insert(upload);
                //repo.deleteAll();
            }
            //finish();
        } else {
            Toast.makeText(this, "You need to select an image!", Toast.LENGTH_SHORT).show();
        }
    }

}