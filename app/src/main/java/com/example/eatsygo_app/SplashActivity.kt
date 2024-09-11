package com.example.eatsygo_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.eatsygo_app.onborading.ViewPagerActivity

class SplashActivity : AppCompatActivity() {
    lateinit var logo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        logo=findViewById(R.id.logo)

        //apply animation in logo
        val animation = AnimationUtils.loadAnimation(this, R.anim.blink_animation)
        // Start the animation on the logo ImageView
        logo.startAnimation(animation)


        Handler(Looper.getMainLooper()).postDelayed({startActivity()},2000)

    }

    private fun startActivity()
    {
        var intent= Intent(this, ViewPagerActivity::class.java)
        startActivity(intent)
        finish() // this function to end splash screen not return to it again
    }
}