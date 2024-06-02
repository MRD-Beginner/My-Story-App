package com.bangkit.storyapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.storyapp.connection.pref.Result
import com.bangkit.storyapp.connection.repository.AppRepository
import com.bangkit.storyapp.connection.response.StoryDetail
import kotlinx.coroutines.launch

class MapsViewModel(private val repository: AppRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _storiesLocation = MutableLiveData<List<StoryDetail>>()
    val storiesLocation: LiveData<List<StoryDetail>> = _storiesLocation

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage
    fun loadStoriesWithLocation(){
        viewModelScope.launch {
            try {
                val response = repository.getStoriesWithLocation()

                if (response is Result.DataSuccess){
                    val storyLocationResponse = response.data
                    _storiesLocation.value = storyLocationResponse.listStory
                }else if (response is Result.DataError){
                    _errorMessage.value = response.error
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}