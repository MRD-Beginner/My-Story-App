package com.bangkit.storyapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.storyapp.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storyId = intent.getStringExtra("STORY_ID")
        val storyTitle = intent.getStringExtra("STORY_TITLE")
        val storyDescription = intent.getStringExtra("STORY_DESCRIPTION")
        val storyPhotoUrl = intent.getStringExtra("STORY_PHOTO_URL")

        binding.textTitle.text = storyTitle
        binding.detailDescription.text = storyDescription
        Glide.with(this)
            .load(storyPhotoUrl)
            .into(binding.imageDetail)
    }
}
