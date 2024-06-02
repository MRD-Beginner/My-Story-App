package com.bangkit.storyapp.ui.authentication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.storyapp.connection.pref.Result
import com.bangkit.storyapp.connection.pref.UserModel
import com.bangkit.storyapp.connection.repository.AuthRepository
import com.bangkit.storyapp.connection.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = Result.DataLoading
            val result = repository.login(email, password)
            _loginResult.value = result
        }
    }

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveLoginSession(user)
        }
    }
}