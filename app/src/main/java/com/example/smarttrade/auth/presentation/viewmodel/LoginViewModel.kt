package com.example.smarttrade.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttrade.auth.domain.repository.UserRepository
import com.example.smarttrade.auth.presentation.validation.ValidateEmail
import com.example.smarttrade.auth.presentation.validation.ValidatePassword
import com.example.smarttrade.auth.presentation.viewmodel.state.LoginState
import com.example.smarttrade.network.Exception.NetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword
): ViewModel(){

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun updateEmail(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun onLogin() {

        val emailValidation = validateEmail.execute(_state.value.email)
        val passwordValidation = validatePassword.execute(_state.value.password)

        val hasError = listOf(
            emailValidation,
            passwordValidation
        ).any { !it.successful }

        if(hasError) {
            _state.value = _state.value.copy(
                emailError = emailValidation.errorMessage,
                passwordError = passwordValidation.errorMessage
            )
            return
        }

        viewModelScope.launch {
            try {
                val call = userRepository.loginUser(_state.value.email, _state.value.password)
                val response = call.body()
                if(call.isSuccessful && response != null) {
                    
                } else {
                    _state.value = _state.value.copy(
                        errorMessage = "Usuario o contraseña incorrectos"
                    )
                }
            } catch(e: NetworkException) {
                // modal window indicating the error
            }
        }
    }
}
