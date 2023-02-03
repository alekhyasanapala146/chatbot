package com.example.chatbotpoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.chatbotpoc.utils.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setupActionBarWithNavController(navController)

        try{
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_graph) as NavHostFragment
            val inflater = navHostFragment.navController.navInflater
            val graph = inflater.inflate(R.navigation.nav_graphs)
            val preferences = getSharedPreferences("MySharedPref", AppCompatActivity.MODE_PRIVATE)

            if (Util.loggedIn(preferences)){
                graph.setStartDestination(R.id.chat_bot_fragment)
            }else {
                graph.setStartDestination(R.id.login_fragment)
            }

            val navController = navHostFragment.navController
            navController.setGraph(graph, intent.extras)
        }catch (e:Exception)
        {
            e.printStackTrace()
            e.toString()
        }

    }
}