package com.example.chatbotpoc.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatbotpoc.data.model.User
import com.example.chatbotpoc.data.repository.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class UserVM  @Inject constructor():ViewModel() {

    var userData= MutableLiveData<List<User>>()
    lateinit var userDataList: ArrayList<User>
    var mobileNumber: MutableLiveData<String> = MutableLiveData()

    fun geData(noteDatabase: UserDao) {
        userDataList= ArrayList()

        val job= CoroutineScope(Dispatchers.IO).async {
            userData(noteDatabase)
        }

    }

    fun userData(noteDatabase: UserDao) {
        val thread = Thread {
            userDataList = ArrayList()
            noteDatabase.getUserData().forEach {
                val user = User(it.mobileNumber,it.password)
                userDataList.add(user)
                userData.postValue(userDataList)
                mobileNumber.postValue(it.mobileNumber)
            }
        }
        thread.start()
    }
}