package com.example.eatsygo_app.onborading

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.viewpager.widget.ViewPager
import com.example.eatsygo_app.HomeActivity
import com.example.eatsygo_app.R
import com.google.android.material.tabs.TabLayout

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var skip_btn: TextView
    private lateinit var next_btn: AppCompatButton
    private lateinit var getStart_btn: AppCompatButton
    private lateinit var tabIndicator: TabLayout

    private val myList = arrayListOf(
        OnBoradItem("Choose Your Favorite Food", R.drawable.onboard1,"Explore a wide variety of delicious dishes from your favorite local restaurants, all in one app!"),
        OnBoradItem("Easy Payment..", R.drawable.onboard2,"Pay effortlessly with our secure and convenient payment options, making your order experience smooth and stress-free."),
        OnBoradItem("Fast Delivery..", R.drawable.onboard3,"Get your food delivered to your doorstep quickly and reliably, ensuring it arrives hot and fresh every time")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        initValues()

        val adapterViewPager = ViewPagerAdapter(this, myList)
        viewPager.adapter = adapterViewPager

        tabIndicator.setupWithViewPager(viewPager)//This line connects the tabIndicator with the ViewPager


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // You can use this method to handle scroll state if needed.
            }

            override fun onPageSelected(position: Int) {
                // Update the tab indicator
                tabIndicator.selectTab(tabIndicator.getTabAt(position))

                // Check if the last screen is loaded
                if (position == myList.size - 1) {
                    loadedLastScreen()
                } else {
                    showNavigationButtons()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                // You can use this method to handle scroll state if needed.
            }
        })


        /*tabIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == myList.size - 1) {
                    loadedLastScreen()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })*/



        getStart_btn.setOnClickListener {
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
            finish()
        }

        next_btn.setOnClickListener {
            var position = viewPager.currentItem
            if (position < myList.size - 1) {
                position++
                viewPager.currentItem = position
            }

            if (position == myList.size - 1) {
                loadedLastScreen()
            }
        }

        skip_btn.setOnClickListener {
            viewPager.currentItem = myList.size - 1
        }
    }

    private fun initValues() {
        viewPager = findViewById(R.id.screen_viewpager)
        skip_btn = findViewById(R.id.tv_skip)
        next_btn = findViewById(R.id.btn_next)
        getStart_btn = findViewById(R.id.btn_get_started)
        tabIndicator = findViewById(R.id.tab_indicator)
    }

    private fun loadedLastScreen() {
        next_btn.visibility = View.INVISIBLE
        skip_btn.visibility = View.INVISIBLE
        tabIndicator.visibility = View.INVISIBLE
        getStart_btn.visibility = View.VISIBLE
    }
    private fun showNavigationButtons() {
        next_btn.visibility = View.VISIBLE
        skip_btn.visibility = View.VISIBLE
        tabIndicator.visibility = View.VISIBLE
        getStart_btn.visibility = View.INVISIBLE
    }
}
