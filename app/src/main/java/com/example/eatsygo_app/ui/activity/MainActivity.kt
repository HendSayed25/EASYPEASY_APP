package com.example.eatsygo_app.ui.activity

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.eatsygo_app.R
import com.example.eatsygo_app.ui.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.container_main)
        bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setupWithNavController(navController)

    }

    fun hideBottomNavigation() {
        bottomNavigation.visibility = View.GONE
    }

    fun showBottomNavigation() {
        bottomNavigation.visibility = View.VISIBLE
    }
}