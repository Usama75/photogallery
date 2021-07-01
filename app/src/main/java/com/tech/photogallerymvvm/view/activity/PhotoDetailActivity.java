package com.tech.photogallerymvvm.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.tech.photogallerymvvm.R;
import com.tech.photogallerymvvm.databinding.PhotoDetailActivityBinding;
import com.tech.photogallerymvvm.model.PhotoGallery;
import com.tech.photogallerymvvm.utilities.Utilties;
import com.tech.photogallerymvvm.viewmodel.PhotoDetailViewModel;



public class PhotoDetailActivity extends AppCompatActivity {

    private static final String EXTRA_PHOTO = "EXTRA_PHOTO";
    private PhotoDetailActivityBinding photoDetailActivityBinding;

    public static Intent launchDetail(Context context, PhotoGallery photoGallery) {
        Intent intent = new Intent(context, PhotoDetailActivity.class);
        intent.putExtra(EXTRA_PHOTO, photoGallery);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoDetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.photo_detail_activity);
        Utilties.displayHomeAsUpEnabled(PhotoDetailActivity.this);
        getExtrasFromIntent();
    }


    private void getExtrasFromIntent() {
        PhotoGallery photoGallery = (PhotoGallery) getIntent().getSerializableExtra(EXTRA_PHOTO);
        PhotoDetailViewModel photoDetailViewModel = new PhotoDetailViewModel(photoGallery);
        photoDetailActivityBinding.setPhotoDetailViewModel(photoDetailViewModel);
        if (photoGallery != null) setTitle("Photo Viewer");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
