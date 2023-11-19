package com.mintokoneko.minto.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.mintokoneko.minto.databinding.ActivityMainBinding
import com.mintokoneko.minto.utils.USER_PHOTO_SWIPE_THRESHOLD
import com.mintokoneko.minto.utils.getWidth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation(this)
        setSwipeableUserChange(this)
    }

    private fun setSwipeableUserChange(context: Context) {
        val gestureDetector = setGestureDetector(context)
        setTopAppBarPhotoListener(gestureDetector)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTopAppBarPhotoListener(gestureDetector: GestureDetectorCompat) {
        binding.mainContent.appBarLayout.topAppBarUserPhoto.setOnTouchListener { v, event ->
            gestureDetector.onTouchEvent(event)
        }
    }

    private fun setGestureDetector(context: Context): GestureDetectorCompat {
        val gestureDetectorListener = object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                e1?.let {
                    if (e1.y - e2.y > USER_PHOTO_SWIPE_THRESHOLD) {
                        Toast.makeText(context, "Previous user", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Next user", Toast.LENGTH_SHORT).show()
                    }
                }
                return true
            }
        }
        val gestureDetector = GestureDetectorCompat(context, gestureDetectorListener)

        return gestureDetector
    }

    private fun setupNavigation(context: Context) {
        val navHostFragment = supportFragmentManager.findFragmentById(binding.mainContent.fragmentContainer.id) as NavHostFragment
        val navController = navHostFragment.navController

        setupNavigationUi(context, navController)
        setupTopAppBar(navController)
    }

    private fun setupTopAppBar(navController: NavController) {
        val topAppBar = binding.mainContent.appBarLayout.topAppBar
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        topAppBar.setupWithNavController(navController, appBarConfiguration)
        topAppBar.setOnMenuItemClickListener { menuItem ->
            NavigationUI.onNavDestinationSelected(menuItem, navController)  // setup settings nav
            true
        }
    }

    private fun setupNavigationUi(
        context: Context,
        navController: NavController
    ) {
        if (getWidth(context) < 600) {
            binding.mainContent.bottomNavigation?.setupWithNavController(navController)
        } else {
            binding.mainContent.navigationRail?.setupWithNavController(navController)
        }
    }

    private fun showSheet(context: Context) {
        if (getWidth(context) < 600) {
            showBottomSheet(context)
        } else {
            showSideSheet(context)
        }
    }

    private fun showBottomSheet(context: Context) {
        Toast.makeText(context, "Bottom sheet", Toast.LENGTH_SHORT).show()
    }

    private fun showSideSheet(context: Context) {
        Toast.makeText(context, "Side sheet", Toast.LENGTH_SHORT).show()
    }
}