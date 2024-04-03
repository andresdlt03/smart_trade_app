package com.example.smarttrade.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttrade.auth.domain.model.User
import com.example.smarttrade.auth.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientRegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ClientRegisterState())
    val state = _state.asStateFlow()

    fun getUsers() {
        viewModelScope.launch {
            val users: List<User> = userRepository.getUsers()
            _state.update {
                it.copy(users = users)
            }
        }
    }

    fun onRegister() {
        /* TODO */
    }
}