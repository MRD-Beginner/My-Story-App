package com.bangkit.storyapp.connection.repository

import android.content.Context
import com.bangkit.storyapp.connection.pref.UserPreferences
import com.bangkit.storyapp.connection.pref.dataStore
import com.bangkit.storyapp.connection.retrofit.story.StoryConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object AppInjection {
    fun provideRepository(context: Context): AppRepository {
        val userPreference = UserPreferences.getInstance(context.dataStore)
        val user = runBlocking { userPreference.getLoginSession().first() }
        val apiService = StoryConfig.getApiService(user.token)
        return AppRepository(apiService, userPreference)
    }
}
