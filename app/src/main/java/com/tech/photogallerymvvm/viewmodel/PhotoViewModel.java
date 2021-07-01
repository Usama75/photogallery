package com.tech.photogallerymvvm.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.tech.photogallerymvvm.R;
import com.tech.photogallerymvvm.app.PhotoGalleryApplication;
import com.tech.photogallerymvvm.data.PhotoGalleryService;
import com.tech.photogallerymvvm.model.PhotoGallery;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;



public class PhotoViewModel extends Observable {

    public ObservableInt photoProgress;
    public ObservableField<String> messageLabel;
    private final ObservableInt photoList;
    private final ObservableInt photoLabel;
    private Context context;
    private final List<PhotoGallery> photoGalleries;
    private Subscription subscription;

    public PhotoViewModel(Context context) {
        this.context = context;
        photoGalleries = new ArrayList<>();
        photoProgress = new ObservableInt(View.VISIBLE);
        photoList = new ObservableInt(View.GONE);
        photoLabel = new ObservableInt(View.GONE);
        messageLabel = new ObservableField<>("");
        fetchPhotos();
    }

    private void fetchPhotos() {

        unSubscribeFromObservable();
        PhotoGalleryApplication photoGalleryApplication = PhotoGalleryApplication.create(context);
        PhotoGalleryService peopleService = photoGalleryApplication.getPhotoAlbumService();
        subscription = peopleService.fetchPhotos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(photoGalleryApplication.subscribeScheduler())
                .subscribe(new Action1<List<PhotoGallery>>() {
                    @Override
                    public void call(List<PhotoGallery> photoGalleries) {
                        photoProgress.set(View.GONE);
                        photoLabel.set(View.GONE);
                        photoList.set(View.VISIBLE);
                        changePhotos(photoGalleries);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        messageLabel.set(context.getString(R.string.error_loading));
                        photoProgress.set(View.GONE);
                        photoLabel.set(View.VISIBLE);
                        photoList.set(View.GONE);
                    }
                });

    }

    public List<PhotoGallery> getPhotos() {
        return photoGalleries;
    }

    private void unSubscribeFromObservable() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private void changePhotos(List<PhotoGallery> photoGalleries) {
        this.photoGalleries.addAll(photoGalleries);
        setChanged();
        notifyObservers();
    }

    public void reset() {
        unSubscribeFromObservable();
        subscription = null;
        context = null;
    }
}
