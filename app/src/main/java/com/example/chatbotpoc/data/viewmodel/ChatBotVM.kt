package com.example.chatbotpoc.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatbotpoc.data.model.MessageModal

class ChatBotVM : ViewModel() {

    lateinit var userMsg: String
    var messagesList: ArrayList<MessageModal> = ArrayList()
    var _messagesList= MutableLiveData<List<MessageModal>>()

    fun userMsgValid():Boolean{
        return  userMsg.isEmpty()
    }
}