package com.example.chatbotpoc.retrofit

import com.example.chatbotpoc.data.model.MsgModal
import retrofit2.http.GET
import retrofit2.http.Url
import retrofit2.Call

interface APIEndPoint {

    @GET
    suspend fun getMessages(@Url url: String?): MsgModal?
}