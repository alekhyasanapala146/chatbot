package com.example.chatbotpoc.data.repository

import com.example.chatbotpoc.chat_bot_fragment
import com.example.chatbotpoc.data.model.MsgModal
import com.example.chatbotpoc.retrofit.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    suspend fun provideData(): MsgModal? =DataRepositoryImpl(RetrofitHelper.apiService).getMessage(chat_bot_fragment.url)
}