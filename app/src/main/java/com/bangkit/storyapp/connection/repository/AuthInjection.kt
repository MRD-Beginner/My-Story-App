package com.bangkit.storyapp.connection.repository

import android.content.Context
import com.bangkit.storyapp.connection.pref.UserPreferences
import com.bangkit.storyapp.connection.pref.dataStore
import com.bangkit.storyapp.connection.retrofit.authen.AuthConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object AuthInjection {
    fun provideAuthRepository(context: Context): AuthRepository {
        val userPreference = UserPreferences.getInstance(context.dataStore)
        val user = runBlocking { userPreference.getLoginSession().first() }
        val authApiService = AuthConfig.getApiService(user.token)
        return AuthRepository(authApiService, userPreference)
    }
}