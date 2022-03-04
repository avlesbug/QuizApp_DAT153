package com.example.quizapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ImageViewHolder>{

    private List<Upload> mUpl;
    private Context mCont;

    public PictureAdapter(Context context, List<Upload> mUpl) {
        mCont = context;
        this.mUpl = mUpl;
    }


    //generated stuff
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2electribogaloo = LayoutInflater.from(mCont).inflate(R.layout.picture_display, parent, false);
        return new ImageViewHolder(view2electribogaloo);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload uploadCur = mUpl.get(position);
        //holder.textViewname.setText("Name: " + uploadCur.getName()+" Path: "+uploadCur.getImage());
        holder.textViewname.setText(uploadCur.getName());
        //Picasso.with(mCont).load(uploadCur.getImage()).fit().centerCrop().into(holder.imageViewname);
        holder.imageViewname.setImageURI(Uri.parse(uploadCur.getImage()));


    }

    @Override
    public int getItemCount() {
       return mUpl.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewname;
        public ImageView imageViewname;


        public ImageViewHolder(View itemView) {

            super(itemView);

            textViewname = itemView.findViewById(R.id.text_picture_name);
            imageViewname = itemView.findViewById(R.id.picture_view_entry);
        }
    }

    public void setmUpl(List<Upload> mUpl) {
        this.mUpl = mUpl;
    }
}
