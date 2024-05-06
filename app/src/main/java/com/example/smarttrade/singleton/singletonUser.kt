package com.example.smarttrade.singleton

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object UserRegistered {
    private val _value: MutableState<String> = mutableStateOf("")

    var usertype: String
        get() = _value.value
        set(newValue) {
            _value.value = newValue
        }

    var email: String
        get() = _value.value
        set(newValue) {
            _value.value = newValue
        }
}