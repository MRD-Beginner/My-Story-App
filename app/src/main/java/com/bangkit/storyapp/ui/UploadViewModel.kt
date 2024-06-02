package com.bangkit.storyapp.ui

import androidx.lifecycle.ViewModel
import com.bangkit.storyapp.connection.repository.AppRepository
import java.io.File

class UploadViewModel(private val repository: AppRepository) : ViewModel() {
    fun uploadImage(
        file: File,
        description: String,
    ) = repository.uploadImage(file, description)
}