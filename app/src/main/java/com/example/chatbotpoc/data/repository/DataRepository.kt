package com.example.chatbotpoc.data.repository

import com.example.chatbotpoc.data.model.MsgModal

interface DataRepository {
    suspend fun getMessage(url: String): MsgModal?
}