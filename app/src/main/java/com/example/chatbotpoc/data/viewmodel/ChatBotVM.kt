package com.example.chatbotpoc.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatbotpoc.data.model.ChatData
import com.example.chatbotpoc.data.model.MessageModal
import com.example.chatbotpoc.data.repository.ChatDataDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatBotVM @Inject constructor(): ViewModel() {

    lateinit var userMsg: String
    var messagesList: ArrayList<MessageModal> = ArrayList()
    var chatDataList: ArrayList<ChatData> = ArrayList()
    var _messagesList= MutableLiveData<List<MessageModal>>()
    var _chatList= MutableLiveData<List<ChatData>>()

    fun userMsgValid():Boolean{
        return  userMsg.isEmpty()
    }

    fun insertChatData(db: ChatDataDao, message: String?, senderName: String){
        val thread = Thread {
            val chatData = ChatData(message,senderName)
            db.insertChatData(chatData)
        }
        thread.start()
    }

    fun getChatData(db: ChatDataDao){
        val thread = Thread {
            chatDataList = ArrayList()
            messagesList = ArrayList()
            db.getChatData().forEach {
                val chatData = ChatData(it.message,it.senderName,it.id)
                val msgData = MessageModal(it.message,it.senderName)
                chatDataList.add(chatData)
                messagesList.add(msgData)
                var s = "khghj"
            }
            _chatList.postValue(chatDataList)
        }
        thread.start()
    }

    fun deleteChatData(chatDB: ChatDataDao) {
        val thread = Thread {
            chatDB.deleteChatData()
        }
        thread.start()
    }
}