package com.example.chatbotpoc.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "chat_data_table")

data class ChatData(
    val message: String?,
    val senderName: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
