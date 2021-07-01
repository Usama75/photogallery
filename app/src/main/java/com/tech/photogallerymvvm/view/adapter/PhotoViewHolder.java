package com.tech.photogallerymvvm.view.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.tech.photogallerymvvm.databinding.ItemPhotoBinding;
import com.tech.photogallerymvvm.model.PhotoGallery;
import com.tech.photogallerymvvm.viewmodel.PhotoItemViewModel;

class PhotoViewHolder extends RecyclerView.ViewHolder {
    private final ItemPhotoBinding itemPhotoBinding;

    PhotoViewHolder(ItemPhotoBinding itemPhotoBinding) {
        super(itemPhotoBinding.itemPhoto);
        this.itemPhotoBinding = itemPhotoBinding;
    }

    void bindPhoto(PhotoGallery photoGallery) {
        if (itemPhotoBinding.getPhotoItemViewModel() == null) {
            itemPhotoBinding.setPhotoItemViewModel(new PhotoItemViewModel(photoGallery, itemView.getContext()));
        } else {
            itemPhotoBinding.getPhotoItemViewModel().setPhoto(photoGallery);
        }
    }
}

