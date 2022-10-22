package com.marangoz.silo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.marangoz.silo.R

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.bottomNav)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNav,navHostFragment.navController)
    }
}