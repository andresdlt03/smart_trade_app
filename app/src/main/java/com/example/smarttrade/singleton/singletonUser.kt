package com.example.smarttrade.singleton

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object UserLogged {
    private val _value: MutableState<String> = mutableStateOf("")

    var userType: String
        get() = _value.value
        set(newValue) {
            _value.value = newValue
        }

    var email: String
        get() = _value.value
        set(newValue) {
            _value.value = newValue
        }
    var dir: String
        get() = _value.value
        set(newValue) {
            _value.value = newValue
        }

    var fullName: String
        get() = _value.value
        set(newValue) {
            _value.value = newValue
        }
}