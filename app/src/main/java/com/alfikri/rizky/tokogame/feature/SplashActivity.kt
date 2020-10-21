package com.alfikri.rizky.tokogame.feature

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.alfikri.rizky.tokogame.MainActivity
import com.alfikri.rizky.tokogame.R

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_TIME_OUT: Long = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}