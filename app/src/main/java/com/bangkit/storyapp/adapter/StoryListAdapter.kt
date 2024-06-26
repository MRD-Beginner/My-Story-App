package com.bangkit.storyapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.storyapp.connection.response.StoryDetail
import com.bangkit.storyapp.databinding.ItemStoryBinding
import com.bangkit.storyapp.ui.DetailActivity
import com.bumptech.glide.Glide

class StoryListAdapter :
    PagingDataAdapter<StoryDetail, StoryListAdapter.StoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class StoryViewHolder(private val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StoryDetail) {
            binding.tvTitle.text = item.name
            binding.tvDescription.text = item.description
            Glide.with(binding.root.context)
                .load(item.photoUrl)
                .into(binding.ivPhoto)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                    putExtra("STORY_ID", item.id)
                    putExtra("STORY_TITLE", item.name)
                    putExtra("STORY_DESCRIPTION", item.description)
                    putExtra("STORY_PHOTO_URL", item.photoUrl)
                }
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StoryDetail>() {
            override fun areItemsTheSame(oldItem: StoryDetail, newItem: StoryDetail): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: StoryDetail,
                newItem: StoryDetail
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
