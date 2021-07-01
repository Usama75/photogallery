package com.tech.photogallerymvvm.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoGalleryFactory {

    private final static String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static PhotoGalleryService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(PhotoGalleryService.class);
    }
}
