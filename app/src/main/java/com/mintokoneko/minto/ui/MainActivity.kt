package com.mintokoneko.minto.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.sidesheet.SideSheetDialog
import com.mintokoneko.minto.R
import com.mintokoneko.minto.databinding.ActivityMainBinding
import com.mintokoneko.minto.ui.bottom_sheet.BottomSheet
import com.mintokoneko.minto.utils.getWidth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation(this)
    }

    private fun setupNavigation(context: Context) {
        val navHostFragment = supportFragmentManager.findFragmentById(binding.mainContent.fragmentContainer.id) as NavHostFragment
        val navController = navHostFragment.navController

        if (getWidth(context) < 600) {
            binding.mainContent.bottomNavigation?.setupWithNavController(navController)
        } else {
            binding.mainContent.navigationRail?.setupWithNavController(navController)
        }

        val topAppBar = binding.mainContent.topAppBar
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        topAppBar.setupWithNavController(navController, appBarConfiguration)
        topAppBar.setOnMenuItemClickListener { menuItem ->
            NavigationUI.onNavDestinationSelected(menuItem, navController)  // setup settings nav

            if (menuItem.itemId == R.id.user_mi) {
                showSheet(context)
            }
            true
        }
    }

    private fun showSheet(context: Context) {
        if (getWidth(context) < 600) {
            showBottomSheet()
        } else {
            showSideSheet(context)
        }
    }

    private fun showBottomSheet() {
        Toast.makeText(this@MainActivity, "Bottom sheet", Toast.LENGTH_SHORT).show()
    }

    private fun showSideSheet(context: Context) {
        Toast.makeText(this@MainActivity, "Side sheet", Toast.LENGTH_SHORT).show()
    }
}