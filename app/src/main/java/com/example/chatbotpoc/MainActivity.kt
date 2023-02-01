package com.example.chatbotpoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_graph) as NavHostFragment
        val navController = navHostFragment.navController
        navHostFragment.setupWithNavController(navController)*/

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_graph) as NavHostFragment
        val navController:NavController = navHostFragment.navController
        //setupActionBarWithNavController(navController)
    }
}