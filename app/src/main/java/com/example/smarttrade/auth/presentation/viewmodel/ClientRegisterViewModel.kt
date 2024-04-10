package com.example.smarttrade.auth.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.smarttrade.auth.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientRegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
) : RegisterViewModel() {

    private val _state = MutableStateFlow(ClientRegisterState())
    val state = _state.asStateFlow()

    fun updateName(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun updateSurname(surname: String) {
        _state.value = _state.value.copy(surname = surname)
    }

    fun updateEmail(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun updateDni(dni: String) {
        _state.value = _state.value.copy(dni = dni)
    }

    override fun onRegister() {
        viewModelScope.launch {

        }
    }
}