package com.mintokoneko.minto.utils

import android.content.Context

fun getWidth(context: Context): Float {
    val displayMetrics = context.resources.displayMetrics
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
    return dpWidth
}