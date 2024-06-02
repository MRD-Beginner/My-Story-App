package com.bangkit.storyapp.ui

import com.bangkit.storyapp.connection.response.StoryDetail

object DataDummy {

    fun generateDummyStoryResponse(): List<StoryDetail> {
        val items: MutableList<StoryDetail> = arrayListOf()
        for (i in 0..100) {
            val quote = StoryDetail(
                id = i.toString(),
                name = i.toString(),
                description = i.toString(),
                photoUrl = i.toString(),
                createdAt = i.toString(),
                lat = i.toDouble(),
                lon = i.toDouble()
            )
            items.add(quote)
        }
        return items
    }
}