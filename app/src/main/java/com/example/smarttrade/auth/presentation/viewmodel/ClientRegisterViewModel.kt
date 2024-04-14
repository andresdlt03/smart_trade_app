package com.example.smarttrade.auth.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.smarttrade.auth.domain.model.Client
import com.example.smarttrade.auth.domain.repository.UserRepository
import com.example.smarttrade.auth.presentation.validation.ValidateEmail
import com.example.smarttrade.auth.presentation.validation.ValidateNotEmpty
import com.example.smarttrade.auth.presentation.validation.ValidatePassword
import com.example.smarttrade.auth.presentation.viewmodel.state.ClientRegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientRegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateNotEmpty: ValidateNotEmpty
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
        val emailValidation = validateEmail.execute(state.value.email)
        val passwordValidation = validatePassword.execute(state.value.password)
        val nameValidation = validateNotEmpty.execute(state.value.name)
        val surnameValidation = validateNotEmpty.execute(state.value.surname)
        val dniValidation = validateNotEmpty.execute(state.value.dni)

        val hasError = listOf(
            emailValidation,
            passwordValidation,
            nameValidation,
            surnameValidation,
            dniValidation
        ).any { !it.successful }

        if(hasError) {
            _state.value = _state.value.copy(
                emailError = emailValidation.errorMessage,
                passwordError = passwordValidation.errorMessage,
                nameError = nameValidation.errorMessage,
                surnameError = surnameValidation.errorMessage,
                dniError = dniValidation.errorMessage
            )
            return
        }

        viewModelScope.launch {
            val client = Client(
                name = state.value.name,
                surname = state.value.surname,
                email = state.value.email,
                password = state.value.password,
                dni = state.value.dni
            )
            userRepository.registerUser(client, "client")
        }
    }
}