package com.example.chatbotpoc.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatbotpoc.data.model.ChatData
import com.example.chatbotpoc.data.model.MessageModal

@Dao
interface ChatDataDao {

    @Insert
    fun insertChatData(chatData: ChatData)

    @Query(value = "Select * from chat_data_table")
    fun getChatData() : List<ChatData>

    @Query(value = "delete from chat_data_table")
    fun deleteChatData()

}