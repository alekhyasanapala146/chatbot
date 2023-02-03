package com.example.chatbotpoc.utils

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

class Util {
    companion object{

        fun loggedIn(preferences: SharedPreferences):Boolean{
            return preferences.getBoolean("login",false)
        }

    }
}