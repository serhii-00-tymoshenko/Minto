package com.mintokoneko.minto.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mintokoneko.minto.R
import com.mintokoneko.minto.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        closeSplashScreenWithDelay()
    }

    private fun closeSplashScreenWithDelay() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            invokeRegisterTransaction()
            finish()
        }
    }

    private fun invokeRegisterTransaction() {
        val registerActivityIntent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(registerActivityIntent)
    }
}