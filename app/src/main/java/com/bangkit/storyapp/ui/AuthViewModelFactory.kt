package com.bangkit.storyapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.storyapp.connection.repository.AuthInjection
import com.bangkit.storyapp.connection.repository.AuthRepository
import com.bangkit.storyapp.ui.authentication.login.LoginViewModel
import com.bangkit.storyapp.ui.authentication.signup.SignUpViewModel

class AuthViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AuthViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context) = AuthViewModelFactory(
            AuthInjection.provideAuthRepository(context)
        )
    }
}