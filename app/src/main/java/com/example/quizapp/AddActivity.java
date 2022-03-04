package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URI;

public class AddActivity extends AppCompatActivity {

    //if set to 0 user wont be able to pick photo
    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mButtonChoosePicture;
    private Button mButtonSaveEntry;
    // private TextView mTextViewShowDB;
    private EditText mEditTextPictureName;
    private ImageView mImageView;
    private ProgressBar mProgressbar;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        mButtonChoosePicture = findViewById(R.id.button_choose_picture);
        mButtonSaveEntry = findViewById(R.id.button_uploader);

        mEditTextPictureName = findViewById(R.id.edit_text_picture_name);
        mImageView = findViewById(R.id.image_view);
        mProgressbar = findViewById(R.id.progressbar);
        mStorageRef = FirebaseStorage.getInstance().getReference("test");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("test");

        //onclick handlers
        mButtonChoosePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFile();
            }
        });

        mButtonSaveEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveEntry();
            }
        });




    }
    //access phone gallery
    private void OpenFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //retrieving picture, if pick image request is true/1, and result is a picture, and data is not null, then you """download""" the picture
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            mImageView.setImageURI(mImageUri);
        }
    }

    //get fileextension from image
    private String getFileEx(Uri uri){
        ContentResolver contentresolver = getContentResolver();
        MimeTypeMap mimetypemap =MimeTypeMap.getSingleton();
        return mimetypemap.getExtensionFromMimeType(contentresolver.getType(uri));
    }
    //creates a filename based on current time in millisec + filename, so name would be [lotsOfNumbers].png
    private void SaveEntry() {
        if(mImageUri != null) {
            StorageReference imageref = mStorageRef.child(System.currentTimeMillis()
                    +"."+getFileEx(mImageUri));

            imageref.putFile(mImageUri)
                    //on successful entry, print message and set progressbar to 0.  visual thing
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //imageref is the name of the picture that is saved to the database
                            Toast.makeText(AddActivity.this, "Nice you managed to upload an image" + imageref +".",
                                    Toast.LENGTH_LONG).show();
                            Task<Uri> urlTask = taskSnapshot
                                    .getStorage()
                                    .getDownloadUrl();

                            while (!urlTask.isSuccessful());

                            Uri downloadUrl = urlTask.getResult();

                            //Upload upload = new Upload(mEditTextPictureName.getText().toString().trim(),downloadUrl.toString());

                            String uploadId = mDatabaseRef.push().getKey();

                            //mDatabaseRef.child(uploadId).setValue(upload);

                            //commented code from line 139-148 lead to images not loading.

                        finish();
                        }
                        //if failure to upload image, print message
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            //failsafe
        }else {
            Toast.makeText( this,"You need to select an image!", Toast.LENGTH_SHORT).show();
        }
    }
}