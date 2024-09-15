package com.example.eatsygo_app.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.eatsygo_app.R
import com.example.eatsygo_app.ui.fragment.CartFragment
import com.example.eatsygo_app.ui.fragment.FavroiteFragment
import com.example.eatsygo_app.ui.fragment.HomeFragment
import com.example.eatsygo_app.ui.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity: AppCompatActivity() {

    lateinit var bottom_navigation:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation=findViewById(R.id.bottom_navigation)

        bottom_navigation.setOnItemSelectedListener {
            if(it.itemId==R.id.home_navigation){
                pushFragment(HomeFragment())

            }else if(it.itemId==R.id.fav_navigation){
                pushFragment(FavroiteFragment())
            }
            else if(it.itemId==R.id.cart_navigation){
                pushFragment(CartFragment())
            }
            else if(it.itemId==R.id.profile_navigation){
                pushFragment(ProfileFragment())
            }
            return@setOnItemSelectedListener true // to pointer to this item is selected and do the action
        }

        bottom_navigation.selectedItemId=R.id.home_navigation //should be here because he should check first then put it as default


    }
    private fun pushFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container_main,fragment).commit()

    }
}