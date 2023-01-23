package com.example.chatbotpoc.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chatbotpoc.data.model.User
import com.example.chatbotpoc.data.repository.UserDao


@Database(entities = [(User::class)],version = 1)
abstract class AppDb :RoomDatabase()  {

    abstract fun userDao() : UserDao

    companion object {

        @Volatile
        private var INSTANCE: AppDb? = null

        fun getDatabase(context: Context): AppDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): AppDb {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDb::class.java,
                "chat_bot_database"
            )
                .build()
        }
    }

}