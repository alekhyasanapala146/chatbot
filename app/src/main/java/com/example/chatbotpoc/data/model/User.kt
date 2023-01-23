package com.example.chatbotpoc.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data_table")

data class User(
    val mobileNumber: String,
    val password: String,
    @PrimaryKey(autoGenerate = false) val id: Int? = null
)
