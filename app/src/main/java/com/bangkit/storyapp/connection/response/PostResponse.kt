package com.bangkit.storyapp.connection.response

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("message")
    val message: String
)