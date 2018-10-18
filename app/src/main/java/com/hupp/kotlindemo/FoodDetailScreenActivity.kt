package com.hupp.kotlindemo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.viewpagerindicator.CirclePageIndicator
import kotlinx.android.synthetic.main.activity_food_detail_screen.*
import java.util.*

class FoodDetailScreenActivity : AppCompatActivity() {
    private var imageModelArrayList: ArrayList<Int>? = null

    private val myImageList = intArrayOf(R.drawable.coffee, R.drawable.espresso, R.drawable.frenchfries, R.drawable.honey, R.drawable.strawberry, R.drawable.sugar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail_screen)
        imageModelArrayList = ArrayList()
        val bundle = intent.extras
        imageModelArrayList = bundle.getIntegerArrayList("image")
        tvFoodName.text = bundle.getString("name")
        tvFoodDescription.text = bundle.getString("description")
        tvPrice.text = "$".plus(" ").plus(bundle.getFloat("amount").toString())
        init()

    }

    private fun init() {

        mPager = findViewById<ViewPager>(R.id.pager)
        mPager!!.adapter = SlidingImage_Adapter(this@FoodDetailScreenActivity, this.imageModelArrayList!!)

        val indicator = findViewById<CirclePageIndicator>(R.id.indicator)

        indicator.setViewPager(mPager)

        val density = resources.displayMetrics.density

        //Set circle indicator radius
        indicator.setRadius(5 * density)

        NUM_PAGES = imageModelArrayList!!.size

        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            mPager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 1000,3000)

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })

    }

    companion object {

        private var mPager: ViewPager? = null
        private var currentPage = 0
        private var NUM_PAGES = 0
    }

    class SlidingImage_Adapter(private val context: Context, private val imageModelArrayList: ArrayList<Int>) : PagerAdapter() {
        private val inflater: LayoutInflater


        init {
            inflater = LayoutInflater.from(context)
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun getCount(): Int {
            return imageModelArrayList.size
        }

        override fun instantiateItem(view: ViewGroup, position: Int): Any {
            val imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false)!!

            val imageView = imageLayout
                    .findViewById<ImageView>(R.id.image)


            imageView.setImageResource(imageModelArrayList[position])

            view.addView(imageLayout, 0)

            return imageLayout
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

        override fun saveState(): Parcelable? {
            return null
        }

    }
}
