package com.tech.photogallerymvvm.model

import com.google.gson.annotations.Expose
import java.io.Serializable

class PhotoGallery(@field:Expose val title: String, @field:Expose val url: String, @field:Expose val thumbnailUrl: String) : Serializable