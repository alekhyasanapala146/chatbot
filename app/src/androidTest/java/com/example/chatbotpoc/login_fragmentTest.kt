package com.example.chatbotpoc

import com.example.chatbotpoc.data.viewmodel.LoginVM
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Test
import com.google.common.truth.Truth.assertThat

//import org.junit.jupiter.api.Assertions.*

 class login_fragmentTest{

    private val testLogin: login_fragment = login_fragment()
    private val testLoginVM: LoginVM = LoginVM()



    @Test
    fun testMobileNum() {
        val expected = 10
        assertEquals(expected, testLogin.getMobileNumber("1234567890"))
    }

    @Test
    fun testSum() {
        val expected = 1
        assertEquals(expected, testLogin.sum(5,5))
    }


    @Test
    fun emptyMobileReturnsFalse(){
        val result = testLoginVM.validUserInput(
            "",
            "123",
        )
        assertThat(result).isFalse()
    }

     @Test
     fun emptyPasswordReturnsFalse(){
         val result = testLoginVM.validUserInput(
             "1234567890",
             "",
         )
         assertThat(result).isFalse()
     }

     @Test
     fun inputsReturnsTrue(){
         val result = testLoginVM.validUserInput(
             "8466022467",
             "123456",
         )
         assertThat(result).isTrue()
     }

}