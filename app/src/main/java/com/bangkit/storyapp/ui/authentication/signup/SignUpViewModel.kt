package com.bangkit.storyapp.ui.authentication.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.storyapp.connection.repository.AuthRepository
import com.bangkit.storyapp.connection.response.SignUpResponse
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: AuthRepository) : ViewModel() {
    val signUpResponse: LiveData<SignUpResponse> = repository.registerResponse

    fun postRegister(name: String, email: String, pass: String) {
        viewModelScope.launch {
            repository.register(name, email, pass)
        }
    }
}