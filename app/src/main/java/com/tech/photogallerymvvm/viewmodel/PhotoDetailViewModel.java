package com.tech.photogallerymvvm.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.tech.photogallerymvvm.model.PhotoGallery;
import com.squareup.picasso.Picasso;



public class PhotoDetailViewModel {

    private final PhotoGallery photoGallery;

    public PhotoDetailViewModel(PhotoGallery photoGallery) {
        this.photoGallery = photoGallery;
    }

    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    public String getTitle() {
        return photoGallery.getTitle();
    }

    public String getImageUrl() {
        return photoGallery.getUrl();
    }
}
