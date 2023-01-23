package com.example.chatbotpoc.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatbotpoc.data.model.User

@Dao
interface UserDao {

    @Insert
    fun insertUserData(user: User)

    @Query(value = "Select * from user_data_table")
    fun getUserData() : List<User>

    @Query("delete from user_data_table")
    fun deleteAllUsers()
}