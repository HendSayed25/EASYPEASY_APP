package com.example.eatsygo_app.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.eatsygo_app.R
import com.example.eatsygo_app.databinding.ActivitySplashBinding
import com.example.eatsygo_app.utils.SharedPrefsManager

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var _binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        SharedPrefsManager.init(this@SplashActivity)

        //apply animation in logo
        val animation = AnimationUtils.loadAnimation(this, R.anim.blink_animation)
        // Start the animation on the logo ImageView
        _binding.logo.startAnimation(animation)

        Handler(Looper.getMainLooper()).postDelayed({ startActivity() }, 3000)

    }

    private fun startActivity() {

        if (SharedPrefsManager.getBoolean("is_logged_in")) {
            startActivity(Intent(this, MainActivity::class.java))
        } else if (SharedPrefsManager.getBoolean("is_onboarding_done")) {
            startActivity(Intent(this, AuthActivity::class.java))
        } else {
            startActivity(Intent(this, ViewPagerActivity::class.java))
        }

        finish() // this function to end splash screen not return to it again
    }
}