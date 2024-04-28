package com.example.smarttrade.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttrade.auth.domain.repository.UserRepository
import com.example.smarttrade.auth.http.login.LoginFailed
import com.example.smarttrade.auth.http.login.LoginSuccess
import com.example.smarttrade.auth.presentation.validation.ValidateEmail
import com.example.smarttrade.auth.presentation.validation.ValidatePassword
import com.example.smarttrade.auth.presentation.viewmodel.state.LoginState
import com.example.smarttrade.network.Exception.NetworkException
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val gson: Gson
): ViewModel(){

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private var loggedUserEmail: String? = null
    private var loggedUserType: String? = null

    fun updateEmail(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun clearError() {
        _state.value = _state.value.copy(
            emailError = null,
            passwordError = null,
            loginError = null
        )
    }
    val getUserLoggedInEmail: String?
        get() = loggedUserEmail

    fun getLoggedUserType(): String? {
        return loggedUserType
    }

//    fun isSeller(response: LoginSuccess): Boolean {
//        return response.cif != ""
//
//    }
//
//    fun isClient(response: LoginSuccess): Boolean {
//        return response.dni != ""
//    }
//
//    fun isAdmin(response: LoginSuccess): Boolean {
//        return response.email.startsWith("admin@")
//    }
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
                if(call.isSuccessful) {

                    val responseBody = call.body()
                    val response = gson.fromJson(responseBody, LoginSuccess::class.java)

                    _state.value = _state.value.copy(
                        loginSuccess = true
                    )
                    loggedUserEmail = _state.value.email
                    loggedUserType = determineUserType(response)

                } else {
                    val body = call.errorBody()?.string()
                    val error = gson.fromJson(body, LoginFailed::class.java)
                    _state.value = _state.value.copy(
                        loginError = error.errorMessage
                    )
                }
            } catch(e: NetworkException) {
                println(e.message)
            }
        }
    }
    private fun determineUserType(response: LoginSuccess?): String {
        if (response != null) {
            return when {
                response.dni != "" -> "Client"
                response.cif != "" -> "Seller"
                response.email.startsWith("admin@") -> "Admin"
                else -> "Unknown"
            }
        }
        return "Unknown"
    }
}
