package com.tech.photogallerymvvm.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.tech.photogallerymvvm.model.PhotoGallery;
import com.tech.photogallerymvvm.view.activity.PhotoDetailActivity;
import com.squareup.picasso.Picasso;



public class PhotoItemViewModel extends BaseObservable {

    private PhotoGallery photoGallery;
    private final Context context;

    public PhotoItemViewModel(PhotoGallery photoGallery, Context context) {
        this.photoGallery = photoGallery;
        this.context = context;
    }

    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    public void setPhoto(PhotoGallery photoGallery) {
        this.photoGallery = photoGallery;
        notifyChange();
    }

    public String getImageUrl() {
        return photoGallery.getThumbnailUrl();
    }

    public String getTitle() {
        return photoGallery.getTitle();
    }


    public void onItemClick(View view) {
        context.startActivity(PhotoDetailActivity.launchDetail(view.getContext(), photoGallery));
    }

}
