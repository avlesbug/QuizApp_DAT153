package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewDBActivity extends AppCompatActivity {

    private UploadViewModel uploadViewModel;
    public static final int NEW_UPLOAD_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dbactivity);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UploadListAdapter adapter = new UploadListAdapter(new UploadListAdapter.UploadDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        uploadViewModel = new ViewModelProvider(this).get(UploadViewModel.class);

        uploadViewModel.getAllUploads().observe(this, uploads -> {
            adapter.submitList(uploads);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(NewDBActivity.this, NewAddActivity.class);
            //startActivityForResult(intent, NEW_UPLOAD_ACTIVITY_REQUEST_CODE);
            startActivity(intent);
        });
    }

/*
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_UPLOAD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Upload upload = new Upload("empty","empty");
            System.out.println("Data: " + data.getStringExtra(NewAddActivity.EXTRA_REPLY));
            if(data.getStringExtra(NewAddActivity.EXTRA_REPLY)!=null) {
                System.out.println();
            }
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

 */

}

