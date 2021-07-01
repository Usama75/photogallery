package com.tech.photogallerymvvm.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.photogallerymvvm.R;
import com.tech.photogallerymvvm.databinding.HomeBinding;
import com.tech.photogallerymvvm.view.adapter.PhotoListAdapter;
import com.tech.photogallerymvvm.viewmodel.PhotoViewModel;

import java.util.Observable;
import java.util.Observer;


public class HomeActivity extends AppCompatActivity implements Observer {

    private HomeBinding homeBinding;
    private PhotoViewModel photoViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startDataBinding();
        createPhotoList(homeBinding.listPhoto);
        setUpObserver(photoViewModel);
    }

    private void startDataBinding() {
        homeBinding = DataBindingUtil.setContentView(this, R.layout.home);
        photoViewModel = new PhotoViewModel(this);
        homeBinding.setPhotoViewModel(photoViewModel);
    }

    private void createPhotoList(RecyclerView photoList) {
        PhotoListAdapter adapter = new PhotoListAdapter();
        photoList.setAdapter(adapter);
        photoList.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof PhotoViewModel) {
            PhotoListAdapter peopleAdapter = (PhotoListAdapter) homeBinding.listPhoto.getAdapter();
            PhotoViewModel photoViewModel = (PhotoViewModel) observable;
            peopleAdapter.setPhotos(photoViewModel.getPhotos());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        photoViewModel.reset();
    }
}
