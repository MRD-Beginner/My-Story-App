package com.bangkit.storyapp.connection.response

import com.google.gson.annotations.SerializedName

class StoryResponse (
    @field:SerializedName("listStory")
    val listStory: List<StoryDetail> = emptyList(),

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)
