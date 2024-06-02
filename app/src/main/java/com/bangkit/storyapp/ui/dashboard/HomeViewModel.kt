package com.bangkit.storyapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bangkit.storyapp.connection.repository.AppRepository
import com.bangkit.storyapp.connection.response.StoryDetail

class HomeViewModel(private val repository: AppRepository) : ViewModel() {
    val stories: LiveData<PagingData<StoryDetail>> =
        repository.getStoryPagingSource().cachedIn(viewModelScope)
}
