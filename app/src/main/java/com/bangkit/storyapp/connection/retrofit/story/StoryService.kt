package com.bangkit.storyapp.connection.retrofit.story

import com.bangkit.storyapp.connection.response.PostResponse
import com.bangkit.storyapp.connection.response.StoryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface StoryService{
    @GET("stories")
//  TODO : Request Token Has Been Added In Api Config
    suspend fun getStoryList(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 20
    ): StoryResponse

    @Multipart
    @POST("stories")
    suspend fun uploadStory(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): PostResponse

    @GET("stories")
    suspend fun getStoryListLocation(
        @Query("location") location : Int = 1,
    ): StoryResponse

}
