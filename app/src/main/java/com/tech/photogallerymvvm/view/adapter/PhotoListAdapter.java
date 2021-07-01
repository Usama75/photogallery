package com.tech.photogallerymvvm.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.photogallerymvvm.R;
import com.tech.photogallerymvvm.databinding.ItemPhotoBinding;
import com.tech.photogallerymvvm.model.PhotoGallery;

import java.util.Collections;
import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoViewHolder> {

    private List<PhotoGallery> photoGalleries;

    public PhotoListAdapter() {
        this.photoGalleries = Collections.emptyList();
    }

    @Override
    @NonNull
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPhotoBinding itemPhotoBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_photo,
                parent, false
        );
        return new PhotoViewHolder(itemPhotoBinding);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.bindPhoto(photoGalleries.get(position));
    }

    @Override
    public int getItemCount() {
        return photoGalleries.size();
    }

    public void setPhotos(List<PhotoGallery> photoGalleries) {
        this.photoGalleries = photoGalleries;
        notifyDataSetChanged();
    }

}
