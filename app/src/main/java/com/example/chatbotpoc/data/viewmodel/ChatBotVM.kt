package com.example.chatbotpoc.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatbotpoc.data.model.MessageModal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatBotVM @Inject constructor(): ViewModel() {

    lateinit var userMsg: String
    var messagesList: ArrayList<MessageModal> = ArrayList()
    var _messagesList= MutableLiveData<List<MessageModal>>()

    fun userMsgValid():Boolean{
        return  userMsg.isEmpty()
    }
}