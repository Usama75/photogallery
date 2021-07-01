package com.tech.photogallerymvvm.utilities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

object Utilties {
    @JvmStatic
    fun displayHomeAsUpEnabled(context: Context) {
        val actionBar = (context as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}