package com.example.smarttrade.auth.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.smarttrade.auth.domain.model.Seller
import com.example.smarttrade.auth.domain.repository.UserRepository
import com.example.smarttrade.auth.presentation.validation.ValidateEmail
import com.example.smarttrade.auth.presentation.validation.ValidateNotEmpty
import com.example.smarttrade.auth.presentation.validation.ValidatePassword
import com.example.smarttrade.auth.presentation.viewmodel.state.SellerRegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellerRegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateNotEmpty: ValidateNotEmpty
    ) : RegisterViewModel() {

    private val _state = MutableStateFlow(SellerRegisterState())
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

    fun updateCompanyName(companyName: String) {
        _state.value = _state.value.copy(companyName = companyName)
    }

    fun updateCif(cif: String) {
        _state.value = _state.value.copy(cif = cif)
    }

    override fun onRegister() {

        val emailValidation = validateEmail.execute(_state.value.email)
        val passwordValidation = validatePassword.execute(_state.value.password)
        val nameValidation = validateNotEmpty.execute(_state.value.name)
        val surnameValidation = validateNotEmpty.execute(_state.value.surname)
        val companyNameValidation = validateNotEmpty.execute(_state.value.companyName)
        val cifValidation = validateNotEmpty.execute(_state.value.cif)

        val hasError = listOf(
            emailValidation,
            passwordValidation,
            nameValidation,
            surnameValidation,
            companyNameValidation,
            cifValidation
        ).any { !it.successful }

        if (hasError) {
            _state.value = _state.value.copy(
                emailError = emailValidation.errorMessage,
                passwordError = passwordValidation.errorMessage,
                nameError = nameValidation.errorMessage,
                surnameError = surnameValidation.errorMessage,
                companyNameError = companyNameValidation.errorMessage,
                cifError = cifValidation.errorMessage
            )
            return
        }

        viewModelScope.launch {
            val seller = Seller(
                name = _state.value.name,
                surname = _state.value.surname,
                email = _state.value.email,
                password = _state.value.password,
                company = _state.value.companyName,
                cif = _state.value.cif
            )
            userRepository.registerUser(seller, "seller")
        }
    }
}