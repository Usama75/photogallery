package com.tech.photogallerymvvm.data

import com.tech.photogallerymvvm.model.PhotoGallery
import retrofit2.http.GET
import rx.Observable

interface PhotoGalleryService {
    @GET("photos")
    fun fetchPhotos(): Observable<List<PhotoGallery?>?>?
}