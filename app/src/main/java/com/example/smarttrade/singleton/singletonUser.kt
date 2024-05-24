package com.example.smarttrade.singleton

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object UserLogged {
    private val _userType: MutableState<String> = mutableStateOf("")
    private val _email: MutableState<String> = mutableStateOf("")
    private val _dir: MutableState<String> = mutableStateOf("")
    private val _fullName: MutableState<String> = mutableStateOf("")

    var userType: String
        get() = _userType.value
        set(value) {
            _userType.value = value
        }

    var email: String
        get() = _email.value
        set(value) {
            _email.value = value
        }

    var dir: String
        get() = _dir.value
        set(value) {
            _dir.value = value
        }

    var fullName: String
        get() = _fullName.value
        set(value) {
            _fullName.value = value
        }
}
