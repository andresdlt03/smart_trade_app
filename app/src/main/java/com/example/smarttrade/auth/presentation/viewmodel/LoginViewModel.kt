package com.example.smarttrade.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttrade.auth.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onLogin() {
        viewModelScope.launch {
            userRepository.loginUser(_state.value.email, _state.value.password)
        }
    }
}