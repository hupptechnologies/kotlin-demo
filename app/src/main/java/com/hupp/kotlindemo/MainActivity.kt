package com.hupp.kotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import com.hupp.kotlindemo.fragment.HomeFragment
import com.hupp.kotlindemo.fragment.CartFragment
import com.hupp.kotlindemo.fragment.LikeFragment
import com.hupp.kotlindemo.utilIs.replaceFragmenty
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_navigation_drawer.*
import kotlinx.android.synthetic.main.content_navigation_drawer.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var content: FrameLayout? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragmenty(
                        fragment = HomeFragment(),
                        allowStateLoss = true,
                        containerViewId = R.id.mainContent
                )
                setTitle("Home")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_like -> {
                replaceFragmenty(
                        fragment = CartFragment(),
                        allowStateLoss = true,
                        containerViewId = R.id.mainContent
                )
                setTitle("Like")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cart -> {
                replaceFragmenty(
                        fragment = LikeFragment(),
                        allowStateLoss = true,
                        containerViewId = R.id.mainContent
                )
                setTitle("Cart")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        replaceFragmenty(
                fragment = HomeFragment(),
                allowStateLoss = true,
                containerViewId = R.id.mainContent
        )
        setTitle("Home")
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation_drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.navigation_home -> {
                // Handle the camera action
                replaceFragmenty(
                        fragment = HomeFragment(),
                        allowStateLoss = true,
                        containerViewId = R.id.mainContent
                )
                setTitle("Home")
            }
            R.id.navigation_cart -> {
                replaceFragmenty(
                        fragment = CartFragment(),
                        allowStateLoss = true,
                        containerViewId = R.id.mainContent
                )
                setTitle("Cart")
            }
            R.id.navigation_like -> {
                replaceFragmenty(
                        fragment = LikeFragment(),
                        allowStateLoss = true,
                        containerViewId = R.id.mainContent
                )
                setTitle("Like")
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
