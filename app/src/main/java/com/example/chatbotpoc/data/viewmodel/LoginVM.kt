package com.example.chatbotpoc.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginVM: ViewModel() {

    var mobileNumber: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    fun isMobileNumberValid(): Boolean {
        val number = this.mobileNumber.value.toString()
        return number.isNotEmpty() && number.length == 10
    }

    fun isPasswordValid() : Boolean{
        val password = this.password.value.toString()
        return password.isNotEmpty()
    }
}