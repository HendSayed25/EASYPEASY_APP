package com.example.eatsygo_app.onborading

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.eatsygo_app.R

class ViewPagerAdapter(private val context: Context, private val list:List<OnBoradItem>):
    PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any { //This method is responsible for creating each page view.

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutScreen = inflater.inflate(R.layout.item_design, null)

        val image: ImageView = layoutScreen.findViewById(R.id.item_img)
        val title: TextView = layoutScreen.findViewById(R.id.item_title)
        val desc: TextView =layoutScreen.findViewById(R.id.item_description)

        title.text = list[position].title
        image.setImageResource(list[position].imgId)
        desc.text=list[position].description

        container.addView(layoutScreen)

        return layoutScreen
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {//This method is used to check whether a view is associated with a specific key object returned by instantiateItem().
        return view==`object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) { // This used to removes a page from the ViewPager when it is no longer needed.
        container.removeView(`object` as View)

    }



}