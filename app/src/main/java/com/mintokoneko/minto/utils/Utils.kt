package com.mintokoneko.minto.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

// TODO: Re-work
fun vibrate(context: Context) {
    if (Build.VERSION.SDK_INT >= 26) {
        (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(50)
    }
}

fun getWidth(context: Context): Float {
    val displayMetrics = context.resources.displayMetrics
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
    return dpWidth
}