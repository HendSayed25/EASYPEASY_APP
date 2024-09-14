package com.example.eatsygo_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.eatsygo_app.R
import com.example.eatsygo_app.ui.activity.ViewPagerActivity

class SplashActivity : AppCompatActivity() {
    lateinit var logo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        logo = findViewById(R.id.logo)

        //apply animation in logo
        val animation = AnimationUtils.loadAnimation(this, R.anim.blink_animation)
        // Start the animation on the logo ImageView
        logo.startAnimation(animation)


        Handler(Looper.getMainLooper()).postDelayed({ startActivity() }, 3000)

    }

    private fun startActivity() {
        startActivity(Intent(this, ViewPagerActivity::class.java))
        finish() // this function to end splash screen not return to it again
    }
}