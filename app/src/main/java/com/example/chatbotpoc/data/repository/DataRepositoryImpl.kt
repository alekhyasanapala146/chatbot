package com.example.chatbotpoc.data.repository

import com.example.chatbotpoc.chat_bot_fragment
import com.example.chatbotpoc.data.model.MsgModal
import com.example.chatbotpoc.retrofit.APIEndPoint

class DataRepositoryImpl(private  var data : APIEndPoint) : DataRepository {
    override suspend fun getMessage(url: String): MsgModal? = data.getMessages(url)
}