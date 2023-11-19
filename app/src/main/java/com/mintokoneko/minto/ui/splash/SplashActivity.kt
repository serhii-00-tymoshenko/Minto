package com.mintokoneko.minto.ui.splash

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.mintokoneko.minto.R
import com.mintokoneko.minto.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        launchRegisterActivity()
    }

    private fun launchRegisterActivity() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            val registerActivityIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(registerActivityIntent)
            finish()
        }
    }
}