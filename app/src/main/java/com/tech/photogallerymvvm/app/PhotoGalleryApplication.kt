package com.tech.photogallerymvvm.app

import android.app.Application
import android.content.Context
import com.tech.photogallerymvvm.data.PhotoGalleryFactory
import com.tech.photogallerymvvm.data.PhotoGalleryService
import rx.Scheduler
import rx.schedulers.Schedulers

class PhotoGalleryApplication : Application() {
    private var photoGalleryService: PhotoGalleryService? = null
    private var scheduler: Scheduler? = null
    val photoAlbumService: PhotoGalleryService?
        get() {
            if (photoGalleryService == null) photoGalleryService = PhotoGalleryFactory.create()
            return photoGalleryService
        }

    fun subscribeScheduler(): Scheduler? {
        if (scheduler == null) scheduler = Schedulers.io()
        return scheduler
    }

    companion object {
        @JvmStatic
        fun create(context: Context): PhotoGalleryApplication {
            return Companion[context]
        }

        private operator fun get(context: Context): PhotoGalleryApplication {
            return context.applicationContext as PhotoGalleryApplication
        }
    }
}