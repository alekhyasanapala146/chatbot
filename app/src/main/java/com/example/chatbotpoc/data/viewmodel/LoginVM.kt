package com.example.chatbotpoc.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatbotpoc.data.model.User
import com.example.chatbotpoc.data.repository.UserDao

class LoginVM: ViewModel() {

    lateinit var mobileNumber: String
    lateinit var password: String

    fun isMobileNumberValid(): Boolean {
        val number = this.mobileNumber
        return number.isNotEmpty() && number.length == 10
    }

    fun isPasswordValid() : Boolean{
        val password = this.password
        return password.isNotEmpty()
    }

    fun validUserInput(
        mobileNumber : String,
        password : String
    ) : Boolean {
        // write conditions along with their return statement
        // if username / password / are empty and mobile num length !=10 return false
        var valid:Boolean = true
        if (mobileNumber.isEmpty() || mobileNumber.length<10){
            valid= false
        }
        if (password.isEmpty()){
            valid= false
        }
        return valid
    }

    fun insertData(db: UserDao) {
        val thread = Thread {
            db.deleteAllUsers()
            val user = User(this.mobileNumber,this.password)
            db.insertUserData(user)
        }
        thread.start()
    }
}